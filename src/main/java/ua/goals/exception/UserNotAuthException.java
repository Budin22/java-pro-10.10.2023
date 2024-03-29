package ua.goals.exception;

public class UserNotAuthException extends Exception{
    public UserNotAuthException(String message) {
        super(message);
    }
}
