package io.github.alprkeskin.common.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

public class CustomExceptionTest {
    @Test
    public void testAccessors() {
        CustomException ex = new AuthorizationException("test");
        assertNotNull(ex);
        assertEquals(UNAUTHORIZED, ex.getHttpStatus());
        assertEquals("test", ex.getResponseMessage());
        assertFalse(ex.hasSuppressedException());
    }

    @Test
    public void testMutators() {
        CustomException ex = new AuthorizationException("test");
        ex.setHttpStatus(ACCEPTED);
        ex.setResponseMessage("test2");
        assertEquals(ACCEPTED, ex.getHttpStatus());
        assertEquals("test2", ex.getResponseMessage());
    }
}
