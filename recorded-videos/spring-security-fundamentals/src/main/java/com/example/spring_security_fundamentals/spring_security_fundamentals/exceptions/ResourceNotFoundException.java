package com.example.spring_security_fundamentals.spring_security_fundamentals.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

}
