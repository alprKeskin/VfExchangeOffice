package io.github.alprkeskin.auth.model.supplier;

import io.github.alprkeskin.auth.model.request.LoginRequest;

public final class LoginRequestSupplier {
    private LoginRequestSupplier() {

    }

    public static LoginRequest getModel() {
        LoginRequest request = new LoginRequest();

        request.setEmail("email");
        request.setPassword("password");

        return request;
    }
}
