package io.github.alprkeskin.common.model.role;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class GrantedAuthorityRoleTest {
    @Test
    public void testGetAuthorityRole() {
        assertEquals(AuthorityRole.ADMIN, GrantedAuthorityRole.getAuthorityRole(GrantedAuthorityRole.ADMIN_ROLE));
        assertEquals(AuthorityRole.EMPLOYEE, GrantedAuthorityRole.getAuthorityRole(GrantedAuthorityRole.EMPLOYEE_ROLE));
        assertEquals(AuthorityRole.CUSTOMER, GrantedAuthorityRole.getAuthorityRole(GrantedAuthorityRole.CUSTOMER_ROLE));
        assertNull(GrantedAuthorityRole.getAuthorityRole("test"));
    }
}
