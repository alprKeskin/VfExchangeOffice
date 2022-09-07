package io.github.alprkeskin.auth.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Objects;
import java.util.StringJoiner;

public final class CryptoUtils {
    private static final MessageDigest DIGEST_SHA256 = getSHA256();
    private static final Logger LOGGER = LoggerFactory.getLogger(CryptoUtils.class);

    private CryptoUtils() {

    }

    private static MessageDigest getSHA256() {
        String algorithm = "SHA-256";
        try {
            return MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("Algorithm cannot be found. :: algorithm={}, exception={}", algorithm, e);
            return null;
        }
    }

    public static String encodeWithSHA256(String... args) {
        if(args == null || args.length == 0) {
            String message = "Encryption context is empty.";
            LOGGER.error(message);
            throw new NullPointerException(message);
        }

        StringJoiner joiner = new StringJoiner(":");
        Arrays.stream(args).filter(Objects::nonNull).forEachOrdered(joiner::add);

        byte[] encodedHash = getEncodedHash(joiner.toString(), DIGEST_SHA256);

        return bytesToHex(encodedHash);
    }

    private static byte[] getEncodedHash(String context, MessageDigest digest) {
        return digest.digest(context.getBytes(StandardCharsets.UTF_8));
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);

        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }

        return hexString.toString();
    }

}
