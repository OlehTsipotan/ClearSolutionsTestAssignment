package com.clearsolutions.testassignment.service.validator.request;

import com.clearsolutions.testassignment.configuration.validation.request.ValidationUserSearchDtoMessageConfig;
import com.clearsolutions.testassignment.model.dto.request.UserSearchDto;
import com.clearsolutions.testassignment.service.core.validator.ObjectValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import java.time.LocalDate;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserSearchDtoValidator implements ObjectValidator<UserSearchDto> {

    private final ValidationUserSearchDtoMessageConfig messageConfig;

    @Override
    public boolean validate(UserSearchDto target, Errors errors, Set<?> hints) {
        validateDateOfBirthRange(target.getDateOfBirthFrom(), target.getDateOfBirthTo(), errors);
        return true;
    }

    private void validateDateOfBirthRange(LocalDate dateOfBirthFrom, LocalDate dateOfBirthTo, Errors errors) {
        if (dateOfBirthFrom != null && dateOfBirthTo != null && dateOfBirthFrom.isAfter(dateOfBirthTo)) {
            errors.rejectValue(UserSearchDto.Fields.dateOfBirthFrom, "",
                    messageConfig.getDateOfBirthFromInvalidRange());
        }
    }
}
