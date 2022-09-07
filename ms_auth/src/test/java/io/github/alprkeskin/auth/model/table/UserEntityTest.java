package io.github.alprkeskin.auth.model.table;

import org.junit.jupiter.api.Test;

import static io.github.alprkeskin.auth.model.supplier.UserEntitySupplier.getModel;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserEntityTest {
    @Test
    public void testBuilder() {
        assertNotNull(getModel(7));
    }

    @Test
    public void testConstructors() {
        assertNotNull(new UserEntity());
        assertNotNull(new UserEntity(1, "email", "username", "password", 2));
    }

    @Test
    public void testAccessors() {
        UserEntity entity = getModel(7);

        assertEquals(1, entity.getId());
        assertEquals("email", entity.getEmail());
        assertEquals("username", entity.getUsername());
        assertEquals("password", entity.getPassword());
        assertEquals(7, entity.getRole());
    }

    @Test
    public void testMutators() {
        UserEntity entity = getModel(7);

        entity.setId(2);
        entity.setEmail("testEmail");
        entity.setUsername("testUsername");
        entity.setPassword("testPassword");
        entity.setRole(4);

        assertEquals(2, entity.getId());
        assertEquals("testEmail", entity.getEmail());
        assertEquals("testUsername", entity.getUsername());
        assertEquals("testPassword", entity.getPassword());
        assertEquals(4, entity.getRole());
    }
}
