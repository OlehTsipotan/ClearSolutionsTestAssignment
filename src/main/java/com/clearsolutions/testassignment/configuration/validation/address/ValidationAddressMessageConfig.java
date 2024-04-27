package com.clearsolutions.testassignment.configuration.validation.address;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Getter
@Setter
@Configuration
@PropertySource("classpath:message/validation/address-message.properties")
@ConfigurationProperties(prefix = "address")
public class ValidationAddressMessageConfig {

    private String idNotRequired;

    private String streetRequired;

    private String streetInvalid;

    private String cityRequired;

    private String cityInvalid;

    private String stateRequired;

    private String stateInvalid;

    private String countryRequired;

    private String countryInvalid;

    private String zipCodeRequired;

    private String zipCodeInvalid;

}
