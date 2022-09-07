package io.github.alprkeskin.common.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class InvalidAuthenticationTokenExceptionTest {
    @Test
    public void testConstructors() {
        CustomException ex1 = new InvalidAuthenticationTokenException("test", HttpStatus.ACCEPTED);
        CustomException ex2 = new InvalidAuthenticationTokenException(ex1, "test2", HttpStatus.ACCEPTED);
        assertNotNull(ex1);
        assertNotNull(ex2);
    }
}
