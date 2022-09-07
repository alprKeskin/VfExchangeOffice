package io.github.alprkeskin.auth.model.request;

import org.junit.jupiter.api.Test;

import static io.github.alprkeskin.auth.model.supplier.LoginRequestSupplier.getModel;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LoginRequestTest {
    @Test
    public void testConstructors() {
        assertNotNull(getModel());
    }

    @Test
    public void testAccessors() {
        LoginRequest request = getModel();

        assertEquals("email", request.getEmail());
        assertEquals("password", request.getPassword());
    }

    @Test
    public void testMutators() {
        LoginRequest request = getModel();

        request.setEmail("testEmail");
        request.setPassword("testPassword");

        assertEquals("testEmail", request.getEmail());
        assertEquals("testPassword", request.getPassword());
    }
}
