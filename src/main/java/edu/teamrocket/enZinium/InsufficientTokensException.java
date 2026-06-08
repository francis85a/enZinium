package edu.teamrocket.enZinium;

public class InsufficientTokensException extends RuntimeException {

    public InsufficientTokensException() {
        super();
    }

    public InsufficientTokensException(String message) {
        super(message);
    }
}