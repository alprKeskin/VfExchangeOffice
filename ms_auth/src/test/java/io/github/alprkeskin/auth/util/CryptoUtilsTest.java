package io.github.alprkeskin.auth.util;

import io.github.alprkeskin.auth.util.CryptoUtils;
import org.junit.jupiter.api.Test;

import static io.github.alprkeskin.auth.util.CryptoUtils.encodeWithSHA256;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CryptoUtilsTest {
    @Test
    public void testEncodeWithSHA256() {
        assertThrows(NullPointerException.class, CryptoUtils::encodeWithSHA256);
        assertNotNull(encodeWithSHA256("test1"));
        assertNotNull(encodeWithSHA256("test1", "test2"));
    }
}
