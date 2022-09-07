package io.github.alprkeskin.auth.service;

import io.github.alprkeskin.common.model.response.UserAuthenticationResponse;

public interface AuthTokenService {
    UserAuthenticationResponse doAuthentication(String token);
}
