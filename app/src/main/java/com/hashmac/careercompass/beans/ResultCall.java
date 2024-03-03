package com.hashmac.careercompass.beans;

public class ResultCall {
    private boolean success;
    private String message;

    public ResultCall(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
