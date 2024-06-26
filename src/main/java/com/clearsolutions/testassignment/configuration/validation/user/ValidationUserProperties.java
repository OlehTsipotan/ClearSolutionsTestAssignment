package com.clearsolutions.testassignment.configuration.validation.user;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Getter
@Setter
@Configuration
@PropertySource("classpath:static/validation/user.properties")
@ConfigurationProperties(prefix = "user")
public class ValidationUserProperties {

    private Integer minAgeLimit;
}
