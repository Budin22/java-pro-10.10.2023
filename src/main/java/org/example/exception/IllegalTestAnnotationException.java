package org.example.exception;

public class IllegalTestAnnotationException extends RuntimeException{
    public IllegalTestAnnotationException(String message) {
        super(message);
    }
}
