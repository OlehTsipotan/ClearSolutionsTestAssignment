package com.clearsolutions.testassignment.service.validator;

import com.clearsolutions.testassignment.model.dto.rest.ErrorPropertyDto;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class ValidationTestUtils {

    public ValidationTestUtils() {
    }

    public static void assertErrorPropertyDtoAdded(String expectedTarget, String expectedErrorMessage,
                                                   Set<ErrorPropertyDto> actualErrorProperties) {

        System.out.println(actualErrorProperties);
        assertEquals(1, actualErrorProperties.size());


        Optional<ErrorPropertyDto> errorPropertyDtoOptional = actualErrorProperties.stream().findFirst();
        assertTrue(errorPropertyDtoOptional.isPresent());

        ErrorPropertyDto errorPropertyDto = errorPropertyDtoOptional.get();

        ErrorPropertyDto expectedErrorPropertyDto = new ErrorPropertyDto(expectedTarget, expectedErrorMessage);

        assertEquals(expectedErrorPropertyDto, errorPropertyDto);
    }

}
