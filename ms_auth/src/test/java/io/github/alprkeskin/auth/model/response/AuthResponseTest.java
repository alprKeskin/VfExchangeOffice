package io.github.alprkeskin.auth.model.response;

import io.github.alprkeskin.auth.model.supplier.AuthResponseSupplier;
import io.github.alprkeskin.common.model.response.result.ResultEntities;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AuthResponseTest {
    @Test
    public void testBuilder() {
        assertNotNull(AuthResponseSupplier.getModel());
    }

    @Test
    public void testConstructors() {
        assertNotNull(new AuthResponse());
        assertNotNull(new AuthResponse(ResultEntities.FAIL.getResultEntity(), "email", "token"));
    }

    @Test
    public void testAccessors() {
        AuthResponse response = AuthResponseSupplier.getModel();

        assertEquals(ResultEntities.SUCCESS.getResultEntity(), response.getResult());
        assertEquals("email", response.getEmail());
        assertEquals("token", response.getToken());
    }

    @Test
    public void testMutators() {
        AuthResponse response = AuthResponseSupplier.getModel();

        response.setResult(ResultEntities.FAIL.getResultEntity());
        response.setEmail("testEmail");
        response.setToken("testToken");

        assertEquals(ResultEntities.FAIL.getResultEntity(), response.getResult());
        assertEquals("testEmail", response.getEmail());
        assertEquals("testToken", response.getToken());
    }
}
