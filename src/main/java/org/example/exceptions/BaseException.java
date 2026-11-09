package org.example.exceptions;

import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {
    private final int httpCode;

    public BaseException(String message, int httpCode) {
        super(message);
        this.httpCode = httpCode;
    }
}
