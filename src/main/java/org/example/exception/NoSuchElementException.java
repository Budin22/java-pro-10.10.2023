package org.example.exception;

public class NoSuchElementException extends RuntimeException {
    public NoSuchElementException(String message) {
        super("Product with category " + message + " not found");
    }
}
