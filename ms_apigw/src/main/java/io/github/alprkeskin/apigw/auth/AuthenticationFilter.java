package io.github.alprkeskin.apigw.auth;

import io.github.alprkeskin.common.exception.AuthorizationException;
import io.github.alprkeskin.common.exception.InvalidAuthenticationTokenException;
import io.github.alprkeskin.common.exception.InvalidEndpointException;
import io.github.alprkeskin.common.model.response.UserAuthenticationResponse;
import io.github.alprkeskin.common.model.role.UserType;
import io.github.alprkeskin.apigw.auth.feign.AuthenticationClient;
import io.github.alprkeskin.apigw.map.UriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Service
public class AuthenticationFilter implements WebFilter {
    private static final String HEADER_PREFIX = "Bearer ";

    @Autowired
    private AuthenticationClient authenticationClient;

    @Autowired
    private UriService uriService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String authenticatedEndpoint = getAuthenticatedEndpoint(exchange);
        if (authenticatedEndpoint == null)
            return chain.filter(exchange);

        String token = resolveToken(exchange);

        checkAuthentication(authenticatedEndpoint, authenticationClient.checkAuthentication(token));

        return chain.filter(exchange);
    }

    private String getAuthenticatedEndpoint(ServerWebExchange exchange) {
        HttpMethod httpMethod = exchange.getRequest().getMethod();
        String requestPath = exchange.getRequest().getURI().getPath();

        return uriService.getAuthenticatedEndpoint(httpMethod, requestPath);
    }

    private void checkAuthentication(String authenticatedEndpoint, UserAuthenticationResponse authResponse) {
        checkAuthenticationResponse(authResponse);
        UserType userType = getUserType(authenticatedEndpoint);
        checkUserTypeValidation(userType, authResponse);
    }

    private void checkAuthenticationResponse(UserAuthenticationResponse authResponse) {
        if(authResponse.isAuthorizationFail())
            throw new AuthorizationException(authResponse.getException(), "User credentials are not correct.");
        if(authResponse.isAuthenticationFail())
            throw new InvalidAuthenticationTokenException(authResponse.getException(), "User authorities are failed.",
                    HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);
    }

    private void checkUserTypeValidation(UserType userType, UserAuthenticationResponse authResponse) {
        boolean validRole = authResponse.getRole().checkRole(userType);
        if(!validRole)
            throw new InvalidAuthenticationTokenException("Endpoint is forbidden for this user.",
                    HttpStatus.FORBIDDEN);
    }

    private UserType getUserType(String requestEndpoint) {
        if(!uriService.containsEndpoint(requestEndpoint)) {
            throw new InvalidEndpointException("Endpoint cannot be resolved by gateway. Endpoint: " + requestEndpoint);
        }

        return uriService.getEndpoint(requestEndpoint).getValidUserType();
    }

    private String resolveToken(ServerWebExchange exchange)
    {
        String bearerToken = exchange.getRequest().getHeaders().get("auth-token").get(0);

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(HEADER_PREFIX))
        {
            return bearerToken.substring(7);
        }

        throw new InvalidAuthenticationTokenException("Header parameter auth-token cannot be " +
                "found or resolved as Bearer.", HttpStatus.BAD_REQUEST);
    }
}
