package com.clearsolutions.testassignment.model.dto.rest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.springframework.validation.Errors;

import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldNameConstants
public class ErrorPropertyDto {

    private String target;

    private String message;

    public static Set<ErrorPropertyDto> fromErrors(Errors errors) {
        return errors.getFieldErrors().stream().map(fieldError -> {
            ErrorPropertyDto errorPropertyDto = new ErrorPropertyDto();
            errorPropertyDto.setTarget(fieldError.getField());
            errorPropertyDto.setMessage(fieldError.getDefaultMessage());
            return errorPropertyDto;
        }).collect(Collectors.toSet());
    }

}