package com.clearsolutions.testassignment.service.validator.user;

import com.clearsolutions.testassignment.configuration.validation.user.ValidationUserMessageConfig;
import com.clearsolutions.testassignment.configuration.validation.user.ValidationUserProperties;
import com.clearsolutions.testassignment.model.dto.UserDto;
import com.clearsolutions.testassignment.model.dto.rest.ErrorPropertyDto;
import com.clearsolutions.testassignment.service.core.validator.ValidatorHint;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import static com.clearsolutions.testassignment.service.validator.ValidationTestUtils.assertErrorPropertyDtoAdded;
import static com.clearsolutions.testassignment.testdata.factory.user.UserDataFactory.buildUserDtoForValidation;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class UserDtoValidatorTest {

    @Mock
    private ValidationUserMessageConfig validationUserMessageConfig;

    @Mock
    private ValidationUserProperties validationUserProperties;

    @InjectMocks
    private UserDtoValidator userDtoValidator;

    void mocks() {
        doReturn(18).when(validationUserProperties).getMinAgeLimit();
    }

    @Test
    void validateForCreate_shouldAddErrorPropertyDto_whenIdIsNotNull() {
        String messageError = "Id should be null.";
        doReturn(messageError).when(validationUserMessageConfig).getIdNotRequired();

        UserDto userDto = buildUserDtoForValidation();
        userDto.setId(UUID.randomUUID());

        mocks();

        Errors errors = new BeanPropertyBindingResult(userDto, "target");

        userDtoValidator.validate(userDto, errors, Set.of(ValidatorHint.FOR_CREATE));

        assertErrorPropertyDtoAdded(UserDto.Fields.id, messageError, ErrorPropertyDto.fromErrors(errors));
    }

    @Test
    void validateForUpdate_shouldAddErrorPropertyDto_whenIdIsNotNull() {
        String messageError = "Id should be null.";
        doReturn(messageError).when(validationUserMessageConfig).getIdNotRequired();

        UserDto userDto = buildUserDtoForValidation();
        userDto.setId(UUID.randomUUID());

        mocks();

        Errors errors = new BeanPropertyBindingResult(userDto, "target");

        userDtoValidator.validate(userDto, errors, Set.of(ValidatorHint.FOR_UPDATE));

        assertErrorPropertyDtoAdded(UserDto.Fields.id, messageError, ErrorPropertyDto.fromErrors(errors));
    }

    @Test
    void validateForCreate_shouldAddErrorPropertyDto_whenFirstNameIsNull() {
        String messageError = "First Name should not be null.";
        doReturn(messageError).when(validationUserMessageConfig).getFirstNameRequired();

        UserDto userDto = buildUserDtoForValidation();
        userDto.setFirstName(null);

        mocks();

        Errors errors = new BeanPropertyBindingResult(userDto, "target");

        userDtoValidator.validate(userDto, errors, Set.of(ValidatorHint.FOR_CREATE));

        assertErrorPropertyDtoAdded(UserDto.Fields.firstName, messageError, ErrorPropertyDto.fromErrors(errors));
    }

    @Test
    void validateForCreate_shouldAddErrorPropertyDto_whenFirstNameIsInvalid() {
        String messageError = "First Name is not valid size";
        doReturn(messageError).when(validationUserMessageConfig).getFirstNameInvalid();

        UserDto userDto = buildUserDtoForValidation();
        userDto.setFirstName("a".repeat(256));

        mocks();

        Errors errors = new BeanPropertyBindingResult(userDto, "target");

        userDtoValidator.validate(userDto, errors, Set.of(ValidatorHint.FOR_CREATE));

        assertErrorPropertyDtoAdded(UserDto.Fields.firstName, messageError, ErrorPropertyDto.fromErrors(errors));
    }

    @Test
    void validateForUpdate_shouldAddErrorPropertyDto_whenFirstNameIsInvalid() {
        String messageError = "First Name is not valid size";
        doReturn(messageError).when(validationUserMessageConfig).getFirstNameInvalid();

        UserDto userDto = buildUserDtoForValidation();
        userDto.setFirstName("a".repeat(256));

        mocks();

        Errors errors = new BeanPropertyBindingResult(userDto, "target");

        userDtoValidator.validate(userDto, errors, Set.of(ValidatorHint.FOR_UPDATE));

        assertErrorPropertyDtoAdded(UserDto.Fields.firstName, messageError, ErrorPropertyDto.fromErrors(errors));
    }

    @Test
    void validateForCreate_shouldAddErrorPropertyDto_whenLastNameIsNull() {
        String messageError = "Last Name should not be null.";
        doReturn(messageError).when(validationUserMessageConfig).getLastNameRequired();

        UserDto userDto = buildUserDtoForValidation();
        userDto.setLastName(null);

        mocks();

        Errors errors = new BeanPropertyBindingResult(userDto, "target");

        userDtoValidator.validate(userDto, errors, Set.of(ValidatorHint.FOR_CREATE));

        assertErrorPropertyDtoAdded(UserDto.Fields.lastName, messageError, ErrorPropertyDto.fromErrors(errors));
    }

    @Test
    void validateForCreate_shouldAddErrorPropertyDto_whenLastNameIsInvalid() {
        String messageError = "Last Name is not valid size";
        doReturn(messageError).when(validationUserMessageConfig).getLastNameInvalid();

        UserDto userDto = buildUserDtoForValidation();
        userDto.setLastName("a".repeat(256));

        mocks();

        Errors errors = new BeanPropertyBindingResult(userDto, "target");

        userDtoValidator.validate(userDto, errors, Set.of(ValidatorHint.FOR_CREATE));

        assertErrorPropertyDtoAdded(UserDto.Fields.lastName, messageError, ErrorPropertyDto.fromErrors(errors));
    }

    @Test
    void validateForUpdate_shouldAddErrorPropertyDto_whenLastNameIsInvalid() {
        String messageError = "Last Name is not valid size";
        doReturn(messageError).when(validationUserMessageConfig).getLastNameInvalid();

        UserDto userDto = buildUserDtoForValidation();
        userDto.setLastName("a".repeat(256));

        mocks();

        Errors errors = new BeanPropertyBindingResult(userDto, "target");

        userDtoValidator.validate(userDto, errors, Set.of(ValidatorHint.FOR_UPDATE));

        assertErrorPropertyDtoAdded(UserDto.Fields.lastName, messageError, ErrorPropertyDto.fromErrors(errors));
    }

    @Test
    void validateForCreate_shouldAddErrorPropertyDto_whenEmailIsNull() {
        String messageError = "Email should not be null.";
        doReturn(messageError).when(validationUserMessageConfig).getEmailRequired();

        UserDto userDto = buildUserDtoForValidation();
        userDto.setEmail(null);

        mocks();

        Errors errors = new BeanPropertyBindingResult(userDto, "target");

        userDtoValidator.validate(userDto, errors, Set.of(ValidatorHint.FOR_CREATE));

        assertErrorPropertyDtoAdded(UserDto.Fields.email, messageError, ErrorPropertyDto.fromErrors(errors));
    }

    @Test
    void validateForCreate_shouldAddErrorPropertyDto_whenEmailIsInvalid() {
        String messageError = "Email is not valid.";
        doReturn(messageError).when(validationUserMessageConfig).getEmailInvalid();

        UserDto userDto = buildUserDtoForValidation();
        userDto.setEmail("invalid-email");

        mocks();

        Errors errors = new BeanPropertyBindingResult(userDto, "target");

        userDtoValidator.validate(userDto, errors, Set.of(ValidatorHint.FOR_CREATE));

        assertErrorPropertyDtoAdded(UserDto.Fields.email, messageError, ErrorPropertyDto.fromErrors(errors));
    }

    @Test
    void validateForCUpdate_shouldAddErrorPropertyDto_whenEmailIsInvalid() {
        String messageError = "Email is not valid.";
        doReturn(messageError).when(validationUserMessageConfig).getEmailInvalid();

        UserDto userDto = buildUserDtoForValidation();
        userDto.setEmail("invalid-email");

        mocks();

        Errors errors = new BeanPropertyBindingResult(userDto, "target");

        userDtoValidator.validate(userDto, errors, Set.of(ValidatorHint.FOR_UPDATE));

        assertErrorPropertyDtoAdded(UserDto.Fields.email, messageError, ErrorPropertyDto.fromErrors(errors));
    }

    @Test
    void validateForCreate_shouldAddErrorPropertyDto_whenDateOfBirthIsNull() {
        String messageError = "Date of Birth should not be null.";
        doReturn(messageError).when(validationUserMessageConfig).getDateOfBirthRequired();

        UserDto userDto = buildUserDtoForValidation();
        userDto.setDateOfBirth(null);

        Errors errors = new BeanPropertyBindingResult(userDto, "target");

        userDtoValidator.validate(userDto, errors, Set.of(ValidatorHint.FOR_CREATE));

        assertErrorPropertyDtoAdded(UserDto.Fields.dateOfBirth, messageError, ErrorPropertyDto.fromErrors(errors));
    }

    @Test
    void validateForCreate_shouldAddErrorPropertyDto_whenDateOfBirthIsLessThan18YearsBeforeNow() {
        String messageError = "Date of Birth is not valid.";
        doReturn(messageError).when(validationUserMessageConfig).getDateOfBirthInvalid();

        UserDto userDto = buildUserDtoForValidation();
        userDto.setDateOfBirth(LocalDate.now());

        mocks();

        Errors errors = new BeanPropertyBindingResult(userDto, "target");

        userDtoValidator.validate(userDto, errors, Set.of(ValidatorHint.FOR_CREATE));

        assertErrorPropertyDtoAdded(UserDto.Fields.dateOfBirth, messageError, ErrorPropertyDto.fromErrors(errors));
    }

    @Test
    void validateForUpdate_shouldAddErrorPropertyDto_whenDateOfBirthIsLessThan18YearsBeforeNow() {
        String messageError = "Date of Birth is not valid.";
        doReturn(messageError).when(validationUserMessageConfig).getDateOfBirthInvalid();

        UserDto userDto = buildUserDtoForValidation();
        userDto.setDateOfBirth(LocalDate.now());

        mocks();

        Errors errors = new BeanPropertyBindingResult(userDto, "target");

        userDtoValidator.validate(userDto, errors, Set.of(ValidatorHint.FOR_UPDATE));

        assertErrorPropertyDtoAdded(UserDto.Fields.dateOfBirth, messageError, ErrorPropertyDto.fromErrors(errors));
    }

    @Test
    void validateForCreate_shouldAddErrorPropertyDto_whenPhoneNumberIsInvalid() {
        String messageError = "Phone Number should not be null.";
        doReturn(messageError).when(validationUserMessageConfig).getPhoneNumberInvalid();

        UserDto userDto = buildUserDtoForValidation();
        userDto.setPhoneNumber("1");

        mocks();

        Errors errors = new BeanPropertyBindingResult(userDto, "target");

        userDtoValidator.validate(userDto, errors, Set.of(ValidatorHint.FOR_CREATE));

        assertErrorPropertyDtoAdded(UserDto.Fields.phoneNumber, messageError, ErrorPropertyDto.fromErrors(errors));
    }

    @Test
    void validateForUpdate_shouldAddErrorPropertyDto_whenPhoneNumberIsInvalid() {
        String messageError = "Phone Number should not be null.";
        doReturn(messageError).when(validationUserMessageConfig).getPhoneNumberInvalid();

        UserDto userDto = buildUserDtoForValidation();
        userDto.setPhoneNumber("1");

        mocks();

        Errors errors = new BeanPropertyBindingResult(userDto, "target");

        userDtoValidator.validate(userDto, errors, Set.of(ValidatorHint.FOR_UPDATE));

        assertErrorPropertyDtoAdded(UserDto.Fields.phoneNumber, messageError, ErrorPropertyDto.fromErrors(errors));
    }

}
