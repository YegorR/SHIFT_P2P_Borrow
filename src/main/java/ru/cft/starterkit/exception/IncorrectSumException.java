package ru.cft.starterkit.exception;

public class IncorrectSumException extends Exception {

    public IncorrectSumException() {
    }

    public IncorrectSumException(String message) {
        super(message);
    }

    public IncorrectSumException(String message, Throwable cause) {
        super(message, cause);
    }

}
