package com.eduardoscheffer.oopjavawebservice.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice // intercepta as excecoes que acontecerem
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        var standardError = new StandardError(
                Instant.now(),
                HttpStatus.NOT_FOUND.value(),
                "Resource not found",
                e.getMessage(),
                request.getRequestURI()
        );
        return new ResponseEntity<>(standardError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request) {
        var standardError = new StandardError(
                Instant.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Database error",
                e.getMessage(),
                request.getRequestURI()
        );
        return new ResponseEntity<>(standardError, HttpStatus.BAD_REQUEST);
    }
}
