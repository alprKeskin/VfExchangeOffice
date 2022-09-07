package io.github.alprkeskin.common.model.role;

import lombok.Getter;

@Getter
public enum UserType {
    ADMIN(4), EMPLOYEE(2), CUSTOMER(1);

    private final int value;

    UserType(int value) {
        this.value = value;
    }
}
