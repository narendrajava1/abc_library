package com.abc.exception;

public class ResourceNotFoundException extends RuntimeException {
    private final int errorCode;

    public ResourceNotFoundException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
