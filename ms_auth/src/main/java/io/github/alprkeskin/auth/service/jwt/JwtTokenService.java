package io.github.alprkeskin.auth.service.jwt;

import io.github.alprkeskin.auth.service.AuthTokenService;
import io.github.alprkeskin.common.exception.AuthorizationException;
import io.github.alprkeskin.common.exception.InvalidAuthenticationTokenException;
import io.github.alprkeskin.common.model.response.UserAuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import static org.springframework.http.HttpStatus.NETWORK_AUTHENTICATION_REQUIRED;

@Service
class JwtTokenService implements AuthTokenService
{
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public UserAuthenticationResponse doAuthentication(String token) {
        try {
            checkAuthentication(token);
        } catch (AuthorizationException e) {
            return UserAuthenticationResponse.builder().authorizationFail(true).exception(e).build();
        } catch (Throwable t) {
            return UserAuthenticationResponse.builder().authenticationFail(true).exception(t).build();
        }
        return UserAuthenticationResponse.builder().role(jwtTokenProvider.getAuthorityRole(token)).build();
    }

    public void checkAuthentication(String token) {
        checkTokenValidation(token);
        checkAuthenticationContext(token);
    }

    private void checkAuthenticationContext(String token) {
        Authentication auth = jwtTokenProvider.getAuthentication(token);

        if (auth == null || auth instanceof AnonymousAuthenticationToken)
            throw new InvalidAuthenticationTokenException("Authentication context is invalid",
                    NETWORK_AUTHENTICATION_REQUIRED);

        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    private void checkTokenValidation(String token) {
        checkTokenNullity(token);
        if (!jwtTokenProvider.validateToken(token))
            throw new InvalidAuthenticationTokenException("Token is invalid.", NETWORK_AUTHENTICATION_REQUIRED);
    }

    private void checkTokenNullity(String token) {
        if (!StringUtils.hasText(token))
            throw new InvalidAuthenticationTokenException(
                    "Token is not found. Request is required auth-token.",
                    NETWORK_AUTHENTICATION_REQUIRED);
    }

}
