package io.github.alprkeskin.auth.properties;

import io.github.alprkeskin.auth.properties.JwtProperties;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class JwtPropertiesTest {

    @Test
    public void testAccessorsAndMutators() {
        JwtProperties jwtProperties = new JwtProperties();

        jwtProperties.setSecretKey("secret");
        jwtProperties.setValidityInMs(1L);

        assertEquals("secret", jwtProperties.getSecretKey());
        assertEquals(1L, jwtProperties.getValidityInMs());
    }
}
