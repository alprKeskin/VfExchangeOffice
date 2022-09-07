package io.github.alprkeskin.common.exception;

import org.springframework.http.HttpStatus;

public class InvalidEndpointException extends CustomException {
    public InvalidEndpointException(String responseMessage) {
        this(null, responseMessage);
    }

    public InvalidEndpointException(Throwable cause, String responseMessage) {
        super(cause, responseMessage, HttpStatus.NOT_FOUND);
    }
}
