package com.clearsolutions.testassignment.model.dto.rest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedHashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponseDto {

    private ErrorCode errorCode;

    private String message;

    private Set<ErrorPropertyDto> errors = new LinkedHashSet<>();

}
