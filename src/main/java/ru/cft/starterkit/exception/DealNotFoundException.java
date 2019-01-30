package ru.cft.starterkit.exception;

public class DealNotFoundException extends Exception {

    public DealNotFoundException() {
    }

    public DealNotFoundException(String message) {
        super(message);
    }

    public DealNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
