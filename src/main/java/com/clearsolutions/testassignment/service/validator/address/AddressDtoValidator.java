package com.clearsolutions.testassignment.service.validator.address;


import com.clearsolutions.testassignment.configuration.validation.address.ValidationAddressMessageConfig;
import com.clearsolutions.testassignment.model.dto.AddressDto;
import com.clearsolutions.testassignment.model.dto.UserDto;
import com.clearsolutions.testassignment.service.core.validator.ObjectValidator;
import com.clearsolutions.testassignment.service.core.validator.ValidatorHint;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import java.util.Set;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class AddressDtoValidator implements ObjectValidator<AddressDto> {

    private static final String ZIP_CODE_PATTERN = "^\\d{5}$";

    private final ValidationAddressMessageConfig validationAddressMessageConfig;

    @Override
    public boolean validate(AddressDto target, Errors errors, Set<?> hints) {
        if (hints.contains(ValidatorHint.FOR_CREATE)) {
            validateCreate(target, errors);
            return true;
        }
        if (hints.contains(ValidatorHint.FOR_UPDATE)) {
            validateUpdate(target, errors);
            return true;
        }
        return false;
    }

    private void validateUpdate(AddressDto target, Errors errors) {
        validateId(target.getId(), errors);
        validateStreetForUpdate(target.getStreet(), errors);
        validateCityForUpdate(target.getCity(), errors);
        validateStateForUpdate(target.getState(), errors);
        validateCountryForUpdate(target.getCountry(), errors);
        validateZipCodeForUpdate(target.getZipCode(), errors);
    }

    private void validateCreate(AddressDto target, Errors errors) {
        validateId(target.getId(), errors);
        validateStreetForCreate(target.getStreet(), errors);
        validateCityForCreate(target.getCity(), errors);
        validateStateForCreate(target.getState(), errors);
        validateCountryForCreate(target.getCountry(), errors);
        validateZipCodeForCreate(target.getZipCode(), errors);
    }

    private void validateId(UUID id, Errors errors) {
        if (id != null) {
            errors.rejectValue(UserDto.Fields.id, "", validationAddressMessageConfig.getIdNotRequired());
        }
    }

    private void validateStreetForCreate(String street, Errors errors) {
        if (street == null || street.isEmpty()) {
            errors.rejectValue(AddressDto.Fields.street, "", validationAddressMessageConfig.getStreetRequired());
        } else if (street.length() < 2 || street.length() > 255) {
            errors.rejectValue(AddressDto.Fields.street, "", validationAddressMessageConfig.getStreetInvalid());
        }
    }

    private void validateCityForCreate(String city, Errors errors) {
        if (city == null || city.isEmpty()) {
            errors.rejectValue(AddressDto.Fields.city, "", validationAddressMessageConfig.getCityRequired());
        } else if (city.length() < 2 || city.length() > 255) {
            errors.rejectValue(AddressDto.Fields.city, "", validationAddressMessageConfig.getCityInvalid());
        }
    }

    private void validateStateForCreate(String state, Errors errors) {
        if (state == null || state.isEmpty()) {
            errors.rejectValue(AddressDto.Fields.state, "", validationAddressMessageConfig.getStateRequired());
        } else if (state.length() < 2 || state.length() > 255) {
            errors.rejectValue(AddressDto.Fields.state, "", validationAddressMessageConfig.getStateInvalid());
        }
    }

    private void validateCountryForCreate(String country, Errors errors) {
        if (country == null || country.isEmpty()) {
            errors.rejectValue(AddressDto.Fields.country, "", validationAddressMessageConfig.getCountryRequired());
        } else if (country.length() < 2 || country.length() > 255) {
            errors.rejectValue(AddressDto.Fields.country, "", validationAddressMessageConfig.getCountryInvalid());
        }
    }

    private void validateZipCodeForCreate(String zipCode, Errors errors) {
        if (zipCode == null || zipCode.isEmpty()) {
            errors.rejectValue(AddressDto.Fields.zipCode, "", validationAddressMessageConfig.getZipCodeRequired());
        } else if (!zipCode.matches(ZIP_CODE_PATTERN)) {
            errors.rejectValue(AddressDto.Fields.zipCode, "", validationAddressMessageConfig.getZipCodeInvalid());
        }
    }

    private void validateStreetForUpdate(String street, Errors errors) {
        if (street != null && (street.length() < 2 || street.length() > 255)) {
            errors.rejectValue(AddressDto.Fields.street, "", validationAddressMessageConfig.getStreetInvalid());
        }
    }

    private void validateCityForUpdate(String city, Errors errors) {
        if (city != null && (city.length() < 2 || city.length() > 255)) {
            errors.rejectValue(AddressDto.Fields.city, "", validationAddressMessageConfig.getCityInvalid());
        }
    }

    private void validateStateForUpdate(String state, Errors errors) {
        if (state != null && (state.length() < 2 || state.length() > 255)) {
            errors.rejectValue(AddressDto.Fields.state, "", validationAddressMessageConfig.getStateInvalid());
        }
    }

    private void validateCountryForUpdate(String country, Errors errors) {
        if (country != null && (country.length() < 2 || country.length() > 255)) {
            errors.rejectValue(AddressDto.Fields.country, "", validationAddressMessageConfig.getCountryInvalid());
        }
    }

    private void validateZipCodeForUpdate(String zipCode, Errors errors) {
        if (zipCode != null && !zipCode.matches(ZIP_CODE_PATTERN)) {
            errors.rejectValue(AddressDto.Fields.zipCode, "", validationAddressMessageConfig.getZipCodeInvalid());
        }
    }
}
