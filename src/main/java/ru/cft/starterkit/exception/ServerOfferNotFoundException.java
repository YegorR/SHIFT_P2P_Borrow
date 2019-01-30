package ru.cft.starterkit.exception;

public class ServerOfferNotFoundException extends Exception {

    public ServerOfferNotFoundException() {
    }

    public ServerOfferNotFoundException(String message) {
        super(message);
    }

    public ServerOfferNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
