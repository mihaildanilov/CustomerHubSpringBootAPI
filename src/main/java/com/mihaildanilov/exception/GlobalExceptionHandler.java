package com.mihaildanilov.exception;

import com.mihaildanilov.util.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ApiResponse> handleCustomerNotFoundException(CustomerNotFoundException ex) {
        ApiResponse response = new ApiResponse(false, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(MissingFieldException.class)
    public ResponseEntity<ApiResponse> handleMissingFieldException(MissingFieldException ex) {
        ApiResponse response = new ApiResponse(false, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(IncorrectAgeException.class)
    public ResponseEntity<ApiResponse> handleIncorrectAgeException(IncorrectAgeException ex) {
        ApiResponse response = new ApiResponse(false, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
