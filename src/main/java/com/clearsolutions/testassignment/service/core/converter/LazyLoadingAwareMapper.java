package com.clearsolutions.testassignment.service.core.converter;

import org.hibernate.Hibernate;

import java.util.Collection;

public interface LazyLoadingAwareMapper {

    default boolean isNotLazyLoaded(Collection<?> sourceCollection) {
        return Hibernate.isInitialized(sourceCollection);
    }

}
