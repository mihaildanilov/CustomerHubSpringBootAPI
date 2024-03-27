package com.mihaildanilov.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(String message) {
        super(message);
    }
}