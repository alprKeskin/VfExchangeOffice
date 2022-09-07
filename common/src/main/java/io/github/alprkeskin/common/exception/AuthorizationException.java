package io.github.alprkeskin.common.exception;

import org.springframework.http.HttpStatus;

public class AuthorizationException extends CustomException {
    public AuthorizationException(String responseMessage) {
        this(null, responseMessage);
    }

    public AuthorizationException(Throwable cause, String responseMessage) {
        super(cause, responseMessage, HttpStatus.UNAUTHORIZED);
    }
}
