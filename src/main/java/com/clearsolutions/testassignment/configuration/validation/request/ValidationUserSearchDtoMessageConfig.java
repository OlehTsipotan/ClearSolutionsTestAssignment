package com.clearsolutions.testassignment.configuration.validation.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Getter
@Setter
@Configuration
@PropertySource("classpath:message/validation/user-search-dto-message.properties")
@ConfigurationProperties(prefix = "user-search-dto")
public class ValidationUserSearchDtoMessageConfig {

    private String dateOfBirthFromInvalidRange;

}
