package com.clearsolutions.testassignment.service.core.converter;

public interface ConverterService {

    <T> T convert(Object source, Class<T> targetClass);

    void convert(Object source, Object target);

}