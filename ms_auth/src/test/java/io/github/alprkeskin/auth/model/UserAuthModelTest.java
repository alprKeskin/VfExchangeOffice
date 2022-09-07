package io.github.alprkeskin.auth.model;

import org.junit.jupiter.api.Test;

import static io.github.alprkeskin.auth.model.supplier.UserAuthModelSupplier.getModel;
import static org.junit.jupiter.api.Assertions.*;

public class UserAuthModelTest {
    @Test
    public void testConstructor() {
        assertNotNull(getModel(7));
    }

    @Test
    public void testAccessors() {
        UserAuthModel authModel = getModel(7);

        assertNotNull(authModel.getGrantedAuthoritiesList());
        assertEquals("email", authModel.getEmail());
        assertEquals("password", authModel.getPassword());
        assertEquals(0, getModel(-1).getGrantedAuthoritiesList().size());
    }

    @Test
    public void testMutators() {
        UserAuthModel authModel = getModel(7);

        authModel.setEmail("testEmail");
        authModel.setPassword("testPassword");
        authModel.setGrantedAuthoritiesList(null);

        assertNull(authModel.getGrantedAuthoritiesList());
        assertEquals("testEmail", authModel.getEmail());
        assertEquals("testPassword", authModel.getPassword());
    }

}
