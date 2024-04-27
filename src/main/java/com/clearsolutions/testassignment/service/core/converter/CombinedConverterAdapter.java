package com.clearsolutions.testassignment.service.core.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;

import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
public class CombinedConverterAdapter implements GenericConverter {

    private final CombinedConverter converter;
    private final Set<ConvertiblePair> convertiblePairs;

    public CombinedConverter getConverter() {
        return converter;
    }

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        return convertiblePairs;
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        if (source == null) {
            return convertNullSource(targetType);
        }
        return converter.convert(source);
    }

    public void convert(Object source, Object target) {
        if (source == null) {
            return;
        }
        converter.convert(source, target);
    }

    private Object convertNullSource(TypeDescriptor targetType) {
        if (targetType.getObjectType() == Optional.class) {
            return Optional.empty();
        }
        return null;
    }

}
