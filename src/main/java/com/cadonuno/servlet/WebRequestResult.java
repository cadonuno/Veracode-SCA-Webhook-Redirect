package com.cadonuno.servlet;

public class WebRequestResult {
    private final int returnCode;
    private final String message;

    public WebRequestResult(int returnCode, String message) {
        this.returnCode = returnCode;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public int getReturnCode() {
        return returnCode;
    }
}
