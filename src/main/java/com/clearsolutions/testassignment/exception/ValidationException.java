package com.clearsolutions.testassignment.exception;

import com.clearsolutions.testassignment.model.dto.rest.ErrorCode;
import com.clearsolutions.testassignment.model.dto.rest.ErrorPropertyDto;

import java.util.Set;

public class ValidationException extends ApplicationException {

    private final transient Set<ErrorPropertyDto> errorProperties;

    public ValidationException(ErrorCode errorCode, Set<ErrorPropertyDto> errorProperties) {
        super(errorCode);
        this.errorProperties = errorProperties;
    }

    public Set<ErrorPropertyDto> getErrorProperties() {
        return errorProperties;
    }

}

