package io.github.alprkeskin.common.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class InvalidEndpointExceptionTest {
    @Test
    public void testConstructors() {
        CustomException ex1 = new InvalidEndpointException("test");
        CustomException ex2 = new InvalidEndpointException(ex1, "test2");
        assertNotNull(ex1);
        assertNotNull(ex2);
    }
}
