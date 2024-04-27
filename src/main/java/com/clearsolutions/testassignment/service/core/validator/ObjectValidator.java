package com.clearsolutions.testassignment.service.core.validator;

import org.springframework.core.ResolvableType;
import org.springframework.validation.Errors;

import java.util.Set;

public interface ObjectValidator<T> {

    default boolean supports(Class<?> targetClass) {
        ResolvableType resolvableType = ResolvableType.forClass(getClass()).as(ObjectValidator.class);
        ResolvableType[] generics = resolvableType.getGenerics();

        if (generics.length != 1) {
            throw new IllegalStateException("Failed to determine generics for " + getClass());
        }

        Class<?> targetType = generics[0].resolve();

        if (targetType == null) {
            throw new IllegalStateException("Failed to determine generics for " + getClass());
        }

        return targetType.equals(targetClass);
    }

    boolean validate(T target, Errors errors, Set<?> hints);

}