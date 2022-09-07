package io.github.alprkeskin.auth.model.supplier;

import io.github.alprkeskin.auth.model.request.RegisterRequest;

import static io.github.alprkeskin.common.model.role.AuthorityRole.ADMIN;

public final class RegisterRequestSupplier {
    private RegisterRequestSupplier() {

    }

    public static RegisterRequest getModel() {
        RegisterRequest request = new RegisterRequest();

        request.setEmail("email");
        request.setUsername("username");
        request.setPassword("password");
        request.setRole(ADMIN);

        return request;
    }
}
