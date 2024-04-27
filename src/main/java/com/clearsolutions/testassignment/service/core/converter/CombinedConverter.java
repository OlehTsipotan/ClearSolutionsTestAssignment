package com.clearsolutions.testassignment.service.core.converter;

import com.clearsolutions.testassignment.exception.ApplicationException;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
class CombinedConverter implements CreateConverter<Object, Object>, UpdateConverter<Object, Object> {

    private final CreateConverter<Object, Object> createConverter;
    private final UpdateConverter<Object, Object> updateConverter;

    @Override
    public Object convert(Object source) {
        if (createConverter == null) {
            throw new ApplicationException("Converter from [" + source.getClass() + "] to [?] not found");
        }
        return createConverter.convert(source);
    }

    @Override
    public void convert(Object source, Object target) {
        if (updateConverter == null) {
            throw new ApplicationException(
                    "Converter from [" + source.getClass() + "] to [" + target.getClass() + "] not found");
        }
        updateConverter.convert(source, target);
    }

}
