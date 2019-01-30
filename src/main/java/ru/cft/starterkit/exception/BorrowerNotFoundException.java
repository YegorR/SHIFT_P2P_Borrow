package ru.cft.starterkit.exception;

public class BorrowerNotFoundException extends Exception {

    public BorrowerNotFoundException() {
    }

    public BorrowerNotFoundException(String message) {
        super(message);
    }

    public BorrowerNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
