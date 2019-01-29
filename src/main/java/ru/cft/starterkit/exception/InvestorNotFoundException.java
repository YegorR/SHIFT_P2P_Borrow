package ru.cft.starterkit.exception;

public class InvestorNotFoundException extends Exception {

    public InvestorNotFoundException() {
    }

    public InvestorNotFoundException(String message) {
        super(message);
    }

    public InvestorNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
