package io.github.alprkeskin.common.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Objects;

@Getter
@Setter
public class CustomException extends RuntimeException {
    private String responseMessage;
    private HttpStatus httpStatus;

    public CustomException(String responseMessage) {
        this(null, responseMessage);
    }

    public CustomException(Throwable cause, String responseMessage) {
        this(cause, responseMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public CustomException(Throwable cause, String responseMessage, HttpStatus httpStatus) {
        super(cause);
        this.responseMessage = responseMessage;
        this.httpStatus = httpStatus;
    }

    public boolean hasSuppressedException() {
        return Objects.nonNull(getCause());
    }
}
