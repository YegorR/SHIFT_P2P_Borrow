package ru.cft.starterkit.api;

import com.fasterxml.jackson.annotation.JsonIgnore;

class Error{
    private int status;
    private String message;
    Error(int status, String message){
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}

public class Response {
    public boolean isStatus() {
        return status;
    }

    public Object getData() {
        return data;
    }

    public Error getError() {
        return error;
    }

    private boolean status;
    private Object data;
    private Error error;

    public Response(boolean status_b, Object object, int status_i, String message){
        this.status = status_b;
        this.data = object;
        this.error = new Error(status_i, message);
    }
}
