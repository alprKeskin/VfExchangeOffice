package io.github.alprkeskin.auth.rest;

import io.github.alprkeskin.auth.service.AuthTokenService;
import io.github.alprkeskin.common.model.response.UserAuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping
public class AuthorityController {
    @Autowired
    private AuthTokenService authTokenService;

    @GetMapping("/check")
    @CrossOrigin
    public ResponseEntity<UserAuthenticationResponse> checkAuth(@RequestHeader("token") String token) {
        return ok(authTokenService.doAuthentication(token));
    }
}
