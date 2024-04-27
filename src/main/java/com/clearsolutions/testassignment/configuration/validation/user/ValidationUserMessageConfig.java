package com.clearsolutions.testassignment.configuration.validation.user;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Getter
@Setter
@Configuration
@PropertySource("classpath:message/validation/user-message.properties")
@ConfigurationProperties(prefix = "user")
public class ValidationUserMessageConfig {

    private String idNotRequired;

    private String firstNameRequired;

    private String firstNameInvalid;

    private String lastNameRequired;

    private String lastNameInvalid;

    private String emailRequired;

    private String emailInvalid;

    private String dateOfBirthRequired;

    private String dateOfBirthInvalid;

    private String phoneNumberInvalid;

}
