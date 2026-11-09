package org.example.exceptions;

public class NotFoundException extends BaseException {
    public NotFoundException(String message) {
        super(message, 404);
    }
}
