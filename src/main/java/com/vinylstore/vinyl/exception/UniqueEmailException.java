package com.vinylstore.vinyl.exception;

public class UniqueEmailException extends RuntimeException {
    public UniqueEmailException(String message) {
        super(message);
    }
}
