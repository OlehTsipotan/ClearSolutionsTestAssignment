package com.clearsolutions.testassignment.service.core.converter;

import org.springframework.core.convert.converter.Converter;

public interface CreateConverter<S, T> extends Converter<S, T> {

    T convert(S source);

}
