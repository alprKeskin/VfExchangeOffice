package io.github.alprkeskin.common.exception;

import org.springframework.http.HttpStatus;

public class InvalidAuthenticationTokenException extends CustomException {
    public InvalidAuthenticationTokenException(String responseMessage, HttpStatus httpStatus) {
        this(null, responseMessage, httpStatus);
    }

    public InvalidAuthenticationTokenException(Throwable cause, String responseMessage, HttpStatus httpStatus) {
        super(cause, responseMessage, httpStatus);
    }
}
