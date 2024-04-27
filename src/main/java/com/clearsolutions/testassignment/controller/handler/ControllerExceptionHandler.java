package com.clearsolutions.testassignment.controller.handler;

import com.clearsolutions.testassignment.exception.EntityNotFoundException;
import com.clearsolutions.testassignment.exception.ValidationException;
import com.clearsolutions.testassignment.model.dto.rest.ErrorResponseDto;
import com.clearsolutions.testassignment.service.core.localization.LocalizationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    private final LocalizationService localizationService;

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleException(Exception ex) {
        HttpStatusCode statusCode = HttpStatus.INTERNAL_SERVER_ERROR;

        ErrorResponseDto response = new ErrorResponseDto();
        response.setMessage(localizationService.get("request.internal-error"));

        log.error("handleException. statusCode: {}", statusCode, ex);

        return ResponseEntity.status(statusCode).body(response);
    }

    @ExceptionHandler(ValidationException.class)
    protected ResponseEntity<Object> handleValidationException(ValidationException ex) {
        HttpStatusCode statusCode = HttpStatus.BAD_REQUEST;

        ErrorResponseDto response = new ErrorResponseDto();
        response.setErrorCode(ex.getErrorCode());
        response.setMessage(localizationService.get("request.body.not-valid"));
        response.setErrors(ex.getErrorProperties());

        log.error("handleValidationException. statusCode: {}", response.getErrors(), ex);

        return ResponseEntity.status(statusCode).body(response);
    }


    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex) {
        HttpStatusCode statusCode = HttpStatus.NOT_FOUND;

        ErrorResponseDto response = new ErrorResponseDto();
        response.setErrorCode(ex.getErrorCode());
        response.setMessage(ex.getMessage());

        log.error("handleEntityNotFoundException. statusCode: {}", statusCode, ex);

        return ResponseEntity.status(statusCode).body(response);
    }

}
