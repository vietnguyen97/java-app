package com.devteria.identity_service.exception;

public class AppException extends RuntimeException {
    public AppException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        AppException.errorCode = errorCode;
    }

    private static ErrorCode errorCode;

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        AppException.errorCode = errorCode;
    }
}
