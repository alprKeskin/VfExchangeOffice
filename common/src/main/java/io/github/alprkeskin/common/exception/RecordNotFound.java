package io.github.alprkeskin.common.exception;

import org.springframework.http.HttpStatus;

public class RecordNotFound extends CustomException {
    public RecordNotFound(String responseMessage) {
        this(null, responseMessage, HttpStatus.NOT_FOUND);
    }

    public RecordNotFound(Throwable cause, String responseMessage, HttpStatus httpStatus) {
        super(cause, responseMessage, httpStatus);
    }
}
