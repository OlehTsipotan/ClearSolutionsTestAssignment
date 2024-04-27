package com.clearsolutions.testassignment.service.validator.request;

import com.clearsolutions.testassignment.configuration.validation.request.ValidationUserSearchDtoMessageConfig;
import com.clearsolutions.testassignment.configuration.validation.user.ValidationUserMessageConfig;
import com.clearsolutions.testassignment.model.dto.request.UserSearchDto;
import com.clearsolutions.testassignment.model.dto.rest.ErrorPropertyDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import java.time.LocalDate;
import java.util.Set;

import static com.clearsolutions.testassignment.service.validator.ValidationTestUtils.assertErrorPropertyDtoAdded;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class UserSearchDtoValidatorTest {

    @Mock
    private ValidationUserSearchDtoMessageConfig validationUserSearchDtoMessageConfig;

    @InjectMocks
    private UserSearchDtoValidator userSearchDtoValidator;

    @Test
    void validate_shouldAddErrorPropertyDto_whenDateOfBirthFromAfterDataOfBirthTo() {
        String messageError = "Invalid Date of Birth range.";
        doReturn(messageError).when(validationUserSearchDtoMessageConfig).getDateOfBirthFromInvalidRange();

        UserSearchDto userSearchDto = new UserSearchDto();
        userSearchDto.setDateOfBirthFrom(LocalDate.of(2000, 1, 1));
        userSearchDto.setDateOfBirthTo(LocalDate.of(1999, 1, 1));


        Errors errors = new BeanPropertyBindingResult(userSearchDto, "target");

        userSearchDtoValidator.validate(userSearchDto, errors, Set.of());

        assertErrorPropertyDtoAdded(UserSearchDto.Fields.dateOfBirthFrom, messageError, ErrorPropertyDto.fromErrors(errors));
    }

    @Test
    void validate_shouldNotAddErrorPropertyDtoValidationException_whenUserSearchDtoIsValid() {
        UserSearchDto userSearchDto = new UserSearchDto();

        Errors errors = new BeanPropertyBindingResult(userSearchDto, "target");

        userSearchDtoValidator.validate(userSearchDto, errors, Set.of());

        assertFalse(errors.hasErrors());
    }

}
