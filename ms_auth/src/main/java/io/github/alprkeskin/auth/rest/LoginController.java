package io.github.alprkeskin.auth.rest;

import io.github.alprkeskin.auth.repository.UserRepository;
import io.github.alprkeskin.auth.service.login.LoginService;
import io.github.alprkeskin.auth.model.request.LoginRequest;
import io.github.alprkeskin.auth.model.response.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    @CrossOrigin
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ok(loginService.doLogin(request.getEmail(), request.getPassword()));
    }
}
