package com.foodfix.management.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super("Error: " + message);
    }
}