package com.clearsolutions.testassignment.exception;


import com.clearsolutions.testassignment.model.dto.rest.ErrorCode;

public class EntityNotFoundException extends ApplicationException {

    public EntityNotFoundException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

}
