package com.clearsolutions.testassignment.service.core.converter;

import org.springframework.core.convert.TypeDescriptor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ConverterServiceDefault implements ConverterService {

    private final ConverterRegistry converterRegistry;

    public ConverterServiceDefault(ConverterRegistry converterRegistry) {
        this.converterRegistry = converterRegistry;
    }

    @SuppressWarnings("unchecked")
    public <T> T convert(Object source, Class<T> targetClass) {
        TypeDescriptor sourceType = Objects.requireNonNull(TypeDescriptor.forObject(source));
        TypeDescriptor targetType = TypeDescriptor.valueOf(targetClass);

        CombinedConverterAdapter converter = converterRegistry.find(source, targetClass);
        return (T) converter.convert(source, sourceType, targetType);
    }

    public void convert(Object source, Object target) {
        CombinedConverterAdapter converter = converterRegistry.find(source, target.getClass());
        converter.convert(source, target);
    }

}
