package io.github.alprkeskin.auth.model.supplier;

import io.github.alprkeskin.auth.model.response.AuthResponse;
import io.github.alprkeskin.common.model.response.result.ResultEntities;

public final class AuthResponseSupplier {
    private AuthResponseSupplier() {

    }

    public static AuthResponse getModel() {
        return AuthResponse.builder().result(ResultEntities.SUCCESS.getResultEntity()).email("email").token("token").build();
    }
}
