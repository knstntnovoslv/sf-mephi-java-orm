package org.example.exceptions;

public class AlreadyExistsException extends BaseException {
    public AlreadyExistsException(String message) {
        super(message, 409);
    }
}
