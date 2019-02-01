package ru.cft.starterkit.api;

public class Response {
    static class Error{
        private int status;
        private String message;
        Error(int status, String message){
            this.status = status;
            this.message = message;
        }
    }

    private boolean status;
    private Object data;
    private Error error;

    public Response(boolean status_b, Object object, int status_i, String message){
        this.status = status_b;
        this.data = data;
        this.error = new Error(status_i, message);
    }
}
