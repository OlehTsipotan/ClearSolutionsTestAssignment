package com.clearsolutions.testassignment.service.core.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ValidatorRegistry {

    private final Set<ObjectValidator<?>> validators;
    private final Map<Class<?>, ObjectValidator<?>> validatorsCache = new HashMap<>();

    @SuppressWarnings("unchecked")
    public ObjectValidator<Object> find(Class<?> targetClass) {
        ObjectValidator<?> objectValidator = validatorsCache.get(targetClass);
        if (objectValidator != null) {
            return (ObjectValidator<Object>) objectValidator;
        }

        for (ObjectValidator<?> validator : validators) {
            if (validator.supports(targetClass)) {
                validatorsCache.put(targetClass, validator);
                return (ObjectValidator<Object>) validator;
            }
        }

        throw new IllegalStateException("Validator for [" + targetClass + "] not found");
    }

}
