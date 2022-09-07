package io.github.alprkeskin.common.model.role;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.Objects;

import static io.github.alprkeskin.common.util.OptionUtils.addOption;
import static io.github.alprkeskin.common.util.OptionUtils.checkOption;

@Getter
public enum AuthorityRole {
    ADMIN(GrantedAuthorityRole.ADMIN_ROLE, UserType.ADMIN, UserType.EMPLOYEE, UserType.CUSTOMER), //
    EMPLOYEE(GrantedAuthorityRole.EMPLOYEE_ROLE, UserType.EMPLOYEE, UserType.CUSTOMER), //
    CUSTOMER(GrantedAuthorityRole.CUSTOMER_ROLE, UserType.CUSTOMER);

    private final GrantedAuthority authority;
    private int roleValue;

    AuthorityRole(String role, UserType... userTypes) {
        authority = new SimpleGrantedAuthority(role);
        Arrays.stream(userTypes).filter(Objects::nonNull).forEach(this::addRole);
    }

    private void addRole(UserType userType) {
        roleValue = addOption(roleValue, userType.getValue());
    }

    public boolean checkRole(UserType userType) {
        return checkRole(userType, getRoleValue());
    }

    private static boolean checkRole(UserType userType, int roleValue) {
        return checkOption(roleValue, userType.getValue());
    }

    public static AuthorityRole getRole(int roleValue) {
        if(checkRole(UserType.ADMIN, roleValue))
            return ADMIN;
        if(checkRole(UserType.EMPLOYEE, roleValue))
            return EMPLOYEE;
        return CUSTOMER;
    }
}
