package io.github.alprkeskin.auth.rest;

import io.github.alprkeskin.auth.model.request.RegisterRequest;
import io.github.alprkeskin.auth.model.response.AuthResponse;
import io.github.alprkeskin.auth.service.register.RegisterService;
import io.github.alprkeskin.common.model.response.result.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping
public class RegisterController {
    @Autowired
    private RegisterService registerService;

    @PostMapping("/register")
    @CrossOrigin
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request)
    {
        return ok(registerService.doRegister(request));
    }

    @DeleteMapping("/unregister")
    @CrossOrigin
    public ResponseEntity<ResultEntity> unregister(@RequestHeader("email") String email)
    {
        return ok(registerService.doUnregister(email));
    }
}
