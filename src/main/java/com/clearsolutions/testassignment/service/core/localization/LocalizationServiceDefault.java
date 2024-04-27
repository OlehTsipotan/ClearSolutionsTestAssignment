package com.clearsolutions.testassignment.service.core.localization;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class LocalizationServiceDefault implements LocalizationService {

    private final MessageSource messageSource;

    public LocalizationServiceDefault(@Qualifier("applicationMessageSource") MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public String get(String messageKey) {
        return messageSource.getMessage(messageKey, null, Locale.getDefault());
    }

    @Override
    public String get(String messageKey, List<Object> variables) {
        return messageSource.getMessage(messageKey, variables.toArray(), Locale.getDefault());
    }

    @Override
    public String get(String messageKey, List<Object> variables, Locale locale) {
        return messageSource.getMessage(messageKey, variables.toArray(), locale);
    }

}
