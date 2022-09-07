package io.github.alprkeskin.auth.model.supplier;

import io.github.alprkeskin.auth.model.UserAuthModel;

public final class UserAuthModelSupplier {
    private UserAuthModelSupplier() {

    }

    public static UserAuthModel getModel(int role) {
        return new UserAuthModel(UserEntitySupplier.getModel(role));
    }
}
