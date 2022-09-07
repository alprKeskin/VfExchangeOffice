package io.github.alprkeskin.common.exception;

import org.springframework.http.HttpStatus;

public class RecordTypeNotValidException extends CustomException {
    public RecordTypeNotValidException(String responseMessage) {
        this(responseMessage, HttpStatus.BAD_REQUEST);
    }

    public RecordTypeNotValidException(String responseMessage, HttpStatus httpStatus) {
        this(null, responseMessage, httpStatus);
    }

    public RecordTypeNotValidException(Throwable cause, String responseMessage, HttpStatus httpStatus) {
        super(cause, responseMessage, httpStatus);
    }
}
