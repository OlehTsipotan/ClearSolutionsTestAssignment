package com.clearsolutions.testassignment.service.core.localization;

import java.util.List;
import java.util.Locale;

public interface LocalizationService {

    String get(String messageKey);

    String get(String messageKey, List<Object> variables);

    String get(String messageKey, List<Object> variables, Locale locale);

}