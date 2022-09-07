package io.github.alprkeskin.auth.model.request;

import io.github.alprkeskin.common.model.role.AuthorityRole;
import org.junit.jupiter.api.Test;

import static io.github.alprkeskin.auth.model.supplier.RegisterRequestSupplier.getModel;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RegisterRequestTest {
    @Test
    public void testConstructors() {
        assertNotNull(getModel());
    }

    @Test
    public void testAccessors() {
        RegisterRequest request = getModel();

        assertEquals("email", request.getEmail());
        assertEquals("username", request.getUsername());
        assertEquals("password", request.getPassword());
        assertEquals(AuthorityRole.ADMIN, request.getRole());
    }

    @Test
    public void testMutators() {
        RegisterRequest request = getModel();

        request.setEmail("testEmail");
        request.setUsername("testUsername");
        request.setPassword("testPassword");
        request.setRole(AuthorityRole.EMPLOYEE);

        assertEquals("testEmail", request.getEmail());
        assertEquals("testUsername", request.getUsername());
        assertEquals("testPassword", request.getPassword());
        assertEquals(AuthorityRole.EMPLOYEE, request.getRole());
    }
}
