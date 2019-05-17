package com.vinylstore.vinyl.exception;

public class EmailOrPasswordNotExistsException extends RuntimeException {
    public EmailOrPasswordNotExistsException(String message) {
        super(message);
    }
}
