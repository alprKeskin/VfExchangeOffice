package io.github.alprkeskin.common.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AuthorizationExceptionTest {
    @Test
    public void testConstructors() {
        CustomException ex1 = new AuthorizationException("test");
        CustomException ex2 = new AuthorizationException(ex1, "test2");
        assertNotNull(ex1);
        assertNotNull(ex2);
    }
}
