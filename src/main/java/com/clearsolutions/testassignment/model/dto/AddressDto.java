package com.clearsolutions.testassignment.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

import java.util.UUID;

/**
 * DTO for {@link com.clearsolutions.testassignment.model.entity.AddressEntity}
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
public class AddressDto {

    private UUID id;

    private String street;

    private String city;

    private String state;

    private String country;

    private String zipCode;

}