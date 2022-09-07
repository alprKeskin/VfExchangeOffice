package io.github.alprkeskin.common.model.role;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AuthorityRoleTest {
    @Test
    public void testGetAuthorityRole() {
        Assertions.assertEquals(AuthorityRole.getRole(7), AuthorityRole.getRole(6));
        Assertions.assertEquals(AuthorityRole.getRole(6), AuthorityRole.getRole(4));
        Assertions.assertEquals(AuthorityRole.ADMIN, AuthorityRole.getRole(4));
        Assertions.assertEquals(AuthorityRole.getRole(3), AuthorityRole.getRole(2));
        Assertions.assertEquals(AuthorityRole.EMPLOYEE, AuthorityRole.getRole(2));
        Assertions.assertEquals(AuthorityRole.getRole(1), AuthorityRole.getRole(-1));
        Assertions.assertEquals(AuthorityRole.CUSTOMER, AuthorityRole.getRole(1));
    }

    @Test
    public void testCheckRole() {
        Assertions.assertTrue(AuthorityRole.ADMIN.checkRole(UserType.ADMIN));
        Assertions.assertTrue(AuthorityRole.ADMIN.checkRole(UserType.EMPLOYEE));
        Assertions.assertTrue(AuthorityRole.ADMIN.checkRole(UserType.CUSTOMER));

        Assertions.assertFalse(AuthorityRole.EMPLOYEE.checkRole(UserType.ADMIN));
        Assertions.assertTrue(AuthorityRole.EMPLOYEE.checkRole(UserType.EMPLOYEE));
        Assertions.assertTrue(AuthorityRole.EMPLOYEE.checkRole(UserType.CUSTOMER));

        Assertions.assertFalse(AuthorityRole.CUSTOMER.checkRole(UserType.ADMIN));
        Assertions.assertFalse(AuthorityRole.CUSTOMER.checkRole(UserType.EMPLOYEE));
        Assertions.assertTrue(AuthorityRole.CUSTOMER.checkRole(UserType.CUSTOMER));
    }
}
