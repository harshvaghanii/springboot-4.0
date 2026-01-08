package com.example.spring_security_fundamentals.spring_security_fundamentals.exceptions;

public class InvalidCredentialException extends RuntimeException {

    public InvalidCredentialException(String message) {
        super(message);
    }

}
