package com.clearsolutions.testassignment.service.core.validator;

import com.clearsolutions.testassignment.exception.ValidationException;
import com.clearsolutions.testassignment.model.dto.rest.ErrorCode;
import com.clearsolutions.testassignment.model.dto.rest.ErrorPropertyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class ValidatorServiceDefault implements ValidatorService {

    private final ValidatorRegistry validatorRegistry;

    @Override
    public void validate(Object target, Object... hints) {
        ObjectValidator<Object> objectValidator = validatorRegistry.find(target.getClass());

        Errors errors = new BeanPropertyBindingResult(target, "target");
        boolean validated = objectValidator.validate(target, errors, Set.of(hints));

        if (!validated) {
            throw new IllegalStateException(
                    "Validator for [" + target.getClass() + "] and [" + Set.of(hints) + "] not executed");
        }

        if (errors.hasErrors()) {
            throw new ValidationException(ErrorCode.REQUEST_BODY_NOT_VALID, ErrorPropertyDto.fromErrors(errors));
        }
    }

}