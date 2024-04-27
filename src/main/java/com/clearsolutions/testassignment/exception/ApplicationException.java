package com.clearsolutions.testassignment.exception;

import com.clearsolutions.testassignment.model.dto.rest.ErrorCode;

public class ApplicationException extends RuntimeException {

    private ErrorCode errorCode;

    public ApplicationException(String message) {
        super(message);
    }

    public ApplicationException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public ApplicationException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

}

