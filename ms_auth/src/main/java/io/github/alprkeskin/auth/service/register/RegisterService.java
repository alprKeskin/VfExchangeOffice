package io.github.alprkeskin.auth.service.register;

import io.github.alprkeskin.auth.model.request.RegisterRequest;
import io.github.alprkeskin.auth.model.response.AuthResponse;
import io.github.alprkeskin.auth.model.table.UserEntity;
import io.github.alprkeskin.auth.service.login.LoginService;
import io.github.alprkeskin.auth.service.manager.UserAuthenticationManager;
import io.github.alprkeskin.auth.service.user.UserServiceMediator;
import io.github.alprkeskin.common.model.response.result.ResultEntity;
import io.github.alprkeskin.common.model.response.result.ResultType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {
    @Autowired
    private UserServiceMediator userServiceMediator;
    @Autowired
    private LoginService loginService;
    @Autowired
    private UserAuthenticationManager authManager;

    public AuthResponse doRegister(RegisterRequest request) {
        userServiceMediator.persistUser(convertEntity(request));
        return loginService.doLogin(request.getEmail(), request.getPassword());
    }

    public ResultEntity doUnregister(String email) {
        userServiceMediator.deleteUser(email);
        authManager.unauthenticated(email);

        String message = "Member is unregistered. Ex-member's email is " + email;

        return ResultEntity.builder().resultType(ResultType.SUCCESS).message(message).build();
    }

    private UserEntity convertEntity(RegisterRequest request) {
        return UserEntity.builder() //
                .email(request.getEmail()) //
                .username(request.getUsername()) //
                .password(request.getPassword()) //
                .role(request.getRole().getRoleValue()). //
                        build();
    }
}
