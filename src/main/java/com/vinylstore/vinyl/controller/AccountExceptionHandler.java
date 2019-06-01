package com.vinylstore.vinyl.controller;

import com.vinylstore.vinyl.exception.ErrorResponse;
import com.vinylstore.vinyl.exception.EmailOrPasswordNotExistsException;
import com.vinylstore.vinyl.exception.UniqueEmailException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class AccountExceptionHandler {
    @ExceptionHandler(value = UniqueEmailException.class)
    public ResponseEntity<Object> handleUniqueEmailConstraint(final UniqueEmailException exception) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = TransactionSystemException.class)
    public ResponseEntity<Object> handleConstraintsViolation(final TransactionSystemException exception) {
        ErrorResponse errorResponse;
        Throwable t = exception.getOriginalException().getCause();
        if (t instanceof ConstraintViolationException) {
            errorResponse = new ErrorResponse(((ConstraintViolationException) t).getConstraintViolations().iterator().next().getMessage());
            return new ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
        } else {
            errorResponse = new ErrorResponse(t.getMessage());
            return new ResponseEntity(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ExceptionHandler(value = EmailOrPasswordNotExistsException.class)
    public ResponseEntity<Object> handleEmailOrPasswordExistence(final EmailOrPasswordNotExistsException exception) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}


