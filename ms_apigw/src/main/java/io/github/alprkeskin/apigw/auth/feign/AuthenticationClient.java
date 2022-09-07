package io.github.alprkeskin.apigw.auth.feign;

import io.github.alprkeskin.common.model.response.UserAuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationClient {
    @Autowired
    private AuthenticationFeignClient feignClient;

    public UserAuthenticationResponse checkAuthentication(String token) {
        return feignClient.checkAuth(token).getBody();
    }
}
