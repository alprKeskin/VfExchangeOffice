package io.github.alprkeskin.common.model.role;

import java.util.HashMap;
import java.util.Map;

public final class GrantedAuthorityRole {
    public static final String ADMIN_ROLE = "ROLE_ADMIN";
    public static final String EMPLOYEE_ROLE = "ROLE_EMPLOYEE";
    public static final String CUSTOMER_ROLE = "ROLE_CUSTOMER";

    private static final Map<String, AuthorityRole> AUTHORITY_ROLE_MAP = new HashMap<>();

    static {
        AUTHORITY_ROLE_MAP.put(ADMIN_ROLE, AuthorityRole.ADMIN);
        AUTHORITY_ROLE_MAP.put(EMPLOYEE_ROLE, AuthorityRole.EMPLOYEE);
        AUTHORITY_ROLE_MAP.put(CUSTOMER_ROLE, AuthorityRole.CUSTOMER);
    }

    private GrantedAuthorityRole() {

    }

    public static AuthorityRole getAuthorityRole(String role) {
        return AUTHORITY_ROLE_MAP.get(role);
    }

}
