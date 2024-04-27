package com.clearsolutions.testassignment.service.core.converter;

public interface UpdateConverter<S, T> {

    void convert(S source, T target);

}
