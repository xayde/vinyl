package com.vinylstore.vinyl.controller;

import com.vinylstore.vinyl.exception.EmailOrPasswordNotExistsException;
import com.vinylstore.vinyl.exception.UniqueEmailException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class AccountExceptionController {
    @ExceptionHandler(value = UniqueEmailException.class)
    public ResponseEntity<Object> handleUniqueEmailConstraint(final UniqueEmailException exception) {
        return new ResponseEntity<>("Email address already exists", HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = TransactionSystemException.class)
    public ResponseEntity<Object> handleConstraintsViolation(final TransactionSystemException exception) {

        Throwable t = exception.getOriginalException().getCause();
        if (t instanceof ConstraintViolationException) {
            return new ResponseEntity(((ConstraintViolationException) t).getConstraintViolations().iterator().next().getMessage(), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity(t.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ExceptionHandler(value = EmailOrPasswordNotExistsException.class)
    public ResponseEntity<Object> handleEmailOrPasswordExistence(final EmailOrPasswordNotExistsException exception) {
        return new ResponseEntity<>("Email or password does not exist", HttpStatus.BAD_REQUEST);
    }
}


