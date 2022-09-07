package io.github.alprkeskin.auth.service.login;

import io.github.alprkeskin.auth.util.CryptoUtils;
import io.github.alprkeskin.auth.model.response.AuthResponse;
import io.github.alprkeskin.auth.service.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import static io.github.alprkeskin.common.model.response.result.ResultEntities.SUCCESS;

@Service
public class LoginService {
    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private JwtTokenProvider tokenProvider;

    public AuthResponse doLogin(String email, String password) {
        String encodedPassword = getEncodedPassword(email, password);

        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(email, encodedPassword));
        String token = tokenProvider.createToken(authentication);

        return AuthResponse.builder().result(SUCCESS.getResultEntity())
                .email(email).token(token).build();
    }

    private String getEncodedPassword(String email, String password) {
        return CryptoUtils.encodeWithSHA256(email, password);
    }
}
