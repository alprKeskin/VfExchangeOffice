package io.github.alprkeskin.auth.model.supplier;

import io.github.alprkeskin.auth.model.table.UserEntity;

public final class UserEntitySupplier {
    private UserEntitySupplier() {

    }

    public static UserEntity getModel(int role) {
        return UserEntity.builder().id(1).email("email").username("username").password("password").role(role).build();
    }
}
