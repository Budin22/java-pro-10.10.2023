package org.example.exception;

public class ArraySizeException extends RuntimeException {
    public ArraySizeException(String errorMessage){
        super(errorMessage);
    }
}
