package com.clearsolutions.testassignment.service.validator.user;

import com.clearsolutions.testassignment.configuration.validation.user.ValidationUserMessageConfig;
import com.clearsolutions.testassignment.configuration.validation.user.ValidationUserProperties;
import com.clearsolutions.testassignment.model.dto.UserDto;
import com.clearsolutions.testassignment.service.core.validator.ObjectValidator;
import com.clearsolutions.testassignment.service.core.validator.ValidatorHint;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import java.time.LocalDate;
import java.time.Period;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserDtoValidator implements ObjectValidator<UserDto> {

    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";

    private static final String PHONE_NUMBER_PATTERN = "^\\d{10}$";

    private final ValidationUserMessageConfig validationUserMessageConfig;

    private final ValidationUserProperties validationUserProperties;

    @Override
    public boolean validate(UserDto target, Errors errors, Set<?> hints) {
        if (hints.contains(ValidatorHint.FOR_CREATE)) {
            validateCreate(target, errors);
            return true;
        }
        if (hints.contains(ValidatorHint.FOR_UPDATE)) {
            validateUpdate(target, errors);
            return true;
        }
        return false;
    }

    private void validateCreate(UserDto userDto, Errors errors) {
        validateId(userDto.getId(), errors);
        validateFirstNameForCreate(userDto.getFirstName(), errors);
        validateLastNameForCreate(userDto.getLastName(), errors);
        validateEmailForCreate(userDto.getEmail(), errors);
        validateDateOfBirthForCreate(userDto.getDateOfBirth(), errors);
        validatePhoneNumber(userDto.getPhoneNumber(), errors);
    }

    private void validateUpdate(UserDto userDto, Errors errors) {
        validateId(userDto.getId(), errors);
        validateFirstNameForUpdate(userDto.getFirstName(), errors);
        validateLastNameForUpdate(userDto.getLastName(), errors);
        validateEmailForUpdate(userDto.getEmail(), errors);
        validateDateOfBirthForUpdate(userDto.getDateOfBirth(), errors);
        validatePhoneNumber(userDto.getPhoneNumber(), errors);
    }

    private void validateId(UUID id, Errors errors) {
        if (id != null) {
            errors.rejectValue(UserDto.Fields.id, "", validationUserMessageConfig.getIdNotRequired());
        }
    }

    private void validateFirstNameForCreate(String firstName, Errors errors) {
        if (firstName == null || firstName.isEmpty()) {
            errors.rejectValue(UserDto.Fields.firstName, "", validationUserMessageConfig.getFirstNameRequired());
        } else if (firstName.length() < 2 || firstName.length() > 255) {
            errors.rejectValue(UserDto.Fields.firstName, "", validationUserMessageConfig.getFirstNameInvalid());
        }
    }

    private void validateFirstNameForUpdate(String firstName, Errors errors) {
        if (firstName != null && (firstName.length() < 2 || firstName.length() > 255)) {
            errors.rejectValue(UserDto.Fields.firstName, "", validationUserMessageConfig.getFirstNameInvalid());
        }
    }

    private void validateLastNameForCreate(String firstName, Errors errors) {
        if (firstName == null || firstName.isEmpty()) {
            errors.rejectValue(UserDto.Fields.lastName, "", validationUserMessageConfig.getLastNameRequired());
        } else if (firstName.length() < 2 || firstName.length() > 255) {
            errors.rejectValue(UserDto.Fields.lastName, "", validationUserMessageConfig.getLastNameInvalid());
        }
    }

    private void validateLastNameForUpdate(String firstName, Errors errors) {
        if (firstName != null && (firstName.length() < 2 || firstName.length() > 255)) {
            errors.rejectValue(UserDto.Fields.lastName, "", validationUserMessageConfig.getLastNameInvalid());
        }
    }

    private void validateEmailForCreate(String email, Errors errors) {
        if (email == null || email.isEmpty()) {
            errors.rejectValue(UserDto.Fields.email, "", validationUserMessageConfig.getEmailRequired());
        } else if (!email.matches(EMAIL_PATTERN)) {
            errors.rejectValue(UserDto.Fields.email, "", validationUserMessageConfig.getEmailInvalid());
        }
    }

    private void validateEmailForUpdate(String email, Errors errors) {
        if (email != null && !email.matches(EMAIL_PATTERN)) {
            errors.rejectValue(UserDto.Fields.email, "", validationUserMessageConfig.getEmailInvalid());
        }
    }

    private void validateDateOfBirthForCreate(LocalDate dateOfBirth, Errors errors) {
        if (dateOfBirth == null) {
            errors.rejectValue(UserDto.Fields.dateOfBirth, "", validationUserMessageConfig.getDateOfBirthRequired());
        } else if (Period.between(dateOfBirth, LocalDate.now()).getYears() <
                validationUserProperties.getMinAgeLimit()) {
            errors.rejectValue(UserDto.Fields.dateOfBirth, "", validationUserMessageConfig.getDateOfBirthInvalid());
        }
    }

    private void validateDateOfBirthForUpdate(LocalDate dateOfBirth, Errors errors) {
        if (dateOfBirth != null &&
                Period.between(dateOfBirth, LocalDate.now()).getYears() < validationUserProperties.getMinAgeLimit()) {
            errors.rejectValue(UserDto.Fields.dateOfBirth, "", validationUserMessageConfig.getDateOfBirthInvalid());
        }
    }

    private void validatePhoneNumber(String phoneNumber, Errors errors) {
        if (phoneNumber != null && !phoneNumber.matches(PHONE_NUMBER_PATTERN)) {
            errors.rejectValue(UserDto.Fields.phoneNumber, "", validationUserMessageConfig.getPhoneNumberInvalid());
        }
    }

}
