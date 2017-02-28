package com.stankiewicz.error;

public class ErrorTemplate {

    private String message;

    public ErrorTemplate(String message){
        this.message = message;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ErrorTemplate{" +
                "message='" + message + '\'' +
                '}';
    }
}
