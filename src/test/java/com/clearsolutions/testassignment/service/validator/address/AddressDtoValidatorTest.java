package com.clearsolutions.testassignment.service.validator.address;

import com.clearsolutions.testassignment.configuration.validation.address.ValidationAddressMessageConfig;
import com.clearsolutions.testassignment.model.dto.AddressDto;
import com.clearsolutions.testassignment.model.dto.rest.ErrorPropertyDto;
import com.clearsolutions.testassignment.service.core.validator.ValidatorHint;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import java.util.Set;
import java.util.UUID;

import static com.clearsolutions.testassignment.service.validator.ValidationTestUtils.assertErrorPropertyDtoAdded;
import static com.clearsolutions.testassignment.testdata.factory.address.AddressDataFactory.buildAddressDtoForValidation;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class AddressDtoValidatorTest {

    @Mock
    private ValidationAddressMessageConfig validationAddressMessageConfig;

    @InjectMocks
    private AddressDtoValidator addressDtoValidator;

    @Test
    void validateForCreate_shouldAddErrorPropertyDto_whenIdIsNotNull() {
        String messageError = "Id should be null.";
        doReturn(messageError).when(validationAddressMessageConfig).getIdNotRequired();

        AddressDto addressDto = buildAddressDtoForValidation();
        addressDto.setId(UUID.randomUUID());

        Errors errors = new BeanPropertyBindingResult(addressDto, "target");

        addressDtoValidator.validate(addressDto, errors, Set.of(ValidatorHint.FOR_CREATE));

        assertErrorPropertyDtoAdded(AddressDto.Fields.id, messageError, ErrorPropertyDto.fromErrors(errors));
    }

    @Test
    void validateForCreate_shouldAddErrorPropertyDto_whenStreetIsNull() {
        String messageError = "Street should not be null";
        doReturn(messageError).when(validationAddressMessageConfig).getStreetRequired();

        AddressDto addressDto = buildAddressDtoForValidation();
        addressDto.setStreet(null);

        Errors errors = new BeanPropertyBindingResult(addressDto, "target");

        addressDtoValidator.validate(addressDto, errors, Set.of(ValidatorHint.FOR_CREATE));

        assertErrorPropertyDtoAdded(AddressDto.Fields.street, messageError, ErrorPropertyDto.fromErrors(errors));
    }

    @Test
    void validateForCreate_shouldAddErrorPropertyDto_whenStreetIsInvalid() {
        String messageError = "Street should be valid";
        doReturn(messageError).when(validationAddressMessageConfig).getStreetInvalid();

        AddressDto addressDto = buildAddressDtoForValidation();
        addressDto.setStreet("A".repeat(256));

        Errors errors = new BeanPropertyBindingResult(addressDto, "target");

        addressDtoValidator.validate(addressDto, errors, Set.of(ValidatorHint.FOR_CREATE));

        assertErrorPropertyDtoAdded(AddressDto.Fields.street, messageError, ErrorPropertyDto.fromErrors(errors));
    }

    @Test
    void validateForCreate_shouldAddErrorPropertyDto_whenCityIsNull() {
        String messageError = "City should not be null";
        doReturn(messageError).when(validationAddressMessageConfig).getCityRequired();

        AddressDto addressDto = buildAddressDtoForValidation();
        addressDto.setCity(null);

        Errors errors = new BeanPropertyBindingResult(addressDto, "target");

        addressDtoValidator.validate(addressDto, errors, Set.of(ValidatorHint.FOR_CREATE));

        assertErrorPropertyDtoAdded(AddressDto.Fields.city, messageError, ErrorPropertyDto.fromErrors(errors));
    }

    @Test
    void validateForCreate_shouldAddErrorPropertyDto_whenCityIsInvalid() {
        String messageError = "City should be valid";
        doReturn(messageError).when(validationAddressMessageConfig).getCityInvalid();

        AddressDto addressDto = buildAddressDtoForValidation();
        addressDto.setCity("A".repeat(256));

        Errors errors = new BeanPropertyBindingResult(addressDto, "target");

        addressDtoValidator.validate(addressDto, errors, Set.of(ValidatorHint.FOR_CREATE));

        assertErrorPropertyDtoAdded(AddressDto.Fields.city, messageError, ErrorPropertyDto.fromErrors(errors));
    }

    @Test
    void validateForCreate_shouldAddErrorPropertyDto_whenStateIsNull() {
        String messageError = "State should not be null";
        doReturn(messageError).when(validationAddressMessageConfig).getStateRequired();

        AddressDto addressDto = buildAddressDtoForValidation();
        addressDto.setState(null);

        Errors errors = new BeanPropertyBindingResult(addressDto, "target");

        addressDtoValidator.validate(addressDto, errors, Set.of(ValidatorHint.FOR_CREATE));

        assertErrorPropertyDtoAdded(AddressDto.Fields.state, messageError, ErrorPropertyDto.fromErrors(errors));
    }

    @Test
    void validateForCreate_shouldAddErrorPropertyDto_whenStateIsInvalid() {
        String messageError = "State should be valid";
        doReturn(messageError).when(validationAddressMessageConfig).getStateInvalid();

        AddressDto addressDto = buildAddressDtoForValidation();
        addressDto.setState("A".repeat(256));

        Errors errors = new BeanPropertyBindingResult(addressDto, "target");

        addressDtoValidator.validate(addressDto, errors, Set.of(ValidatorHint.FOR_CREATE));

        assertErrorPropertyDtoAdded(AddressDto.Fields.state, messageError, ErrorPropertyDto.fromErrors(errors));
    }

    @Test
    void validateForCreate_shouldAddErrorPropertyDto_whenCountryIsNull() {
        String messageError = "Country should not be null";
        doReturn(messageError).when(validationAddressMessageConfig).getCountryRequired();

        AddressDto addressDto = buildAddressDtoForValidation();
        addressDto.setCountry(null);

        Errors errors = new BeanPropertyBindingResult(addressDto, "target");

        addressDtoValidator.validate(addressDto, errors, Set.of(ValidatorHint.FOR_CREATE));

        assertErrorPropertyDtoAdded(AddressDto.Fields.country, messageError, ErrorPropertyDto.fromErrors(errors));
    }

    @Test
    void validateForCreate_shouldAddErrorPropertyDto_whenCountryIsInvalid() {
        String messageError = "Country should be valid";
        doReturn(messageError).when(validationAddressMessageConfig).getCountryInvalid();

        AddressDto addressDto = buildAddressDtoForValidation();
        addressDto.setCountry("A".repeat(256));

        Errors errors = new BeanPropertyBindingResult(addressDto, "target");

        addressDtoValidator.validate(addressDto, errors, Set.of(ValidatorHint.FOR_CREATE));

        assertErrorPropertyDtoAdded(AddressDto.Fields.country, messageError, ErrorPropertyDto.fromErrors(errors));
    }

    @Test
    void validateForCreate_shouldAddErrorPropertyDto_whenZipCodeIsNull() {
        String messageError = "Zip code should not be null";
        doReturn(messageError).when(validationAddressMessageConfig).getZipCodeRequired();

        AddressDto addressDto = buildAddressDtoForValidation();
        addressDto.setZipCode(null);

        Errors errors = new BeanPropertyBindingResult(addressDto, "target");

        addressDtoValidator.validate(addressDto, errors, Set.of(ValidatorHint.FOR_CREATE));

        assertErrorPropertyDtoAdded(AddressDto.Fields.zipCode, messageError, ErrorPropertyDto.fromErrors(errors));
    }

    @Test
    void validateForCreate_shouldAddErrorPropertyDto_whenZipCodeIsInvalid() {
        String messageError = "Zip code should be valid";
        doReturn(messageError).when(validationAddressMessageConfig).getZipCodeInvalid();

        AddressDto addressDto = buildAddressDtoForValidation();
        addressDto.setZipCode("123456");

        Errors errors = new BeanPropertyBindingResult(addressDto, "target");

        addressDtoValidator.validate(addressDto, errors, Set.of(ValidatorHint.FOR_CREATE));

        assertErrorPropertyDtoAdded(AddressDto.Fields.zipCode, messageError, ErrorPropertyDto.fromErrors(errors));
    }

    @Test
    void validateForUpdate_shouldAddErrorPropertyDto_whenStreetIsInvalid() {
        String messageError = "Street should be valid";
        doReturn(messageError).when(validationAddressMessageConfig).getStreetInvalid();

        AddressDto addressDto = buildAddressDtoForValidation();
        addressDto.setStreet("A".repeat(256));

        Errors errors = new BeanPropertyBindingResult(addressDto, "target");

        addressDtoValidator.validate(addressDto, errors, Set.of(ValidatorHint.FOR_UPDATE));

        assertErrorPropertyDtoAdded(AddressDto.Fields.street, messageError, ErrorPropertyDto.fromErrors(errors));
    }

    @Test
    void validateForUpdate_shouldAddErrorPropertyDto_whenCityIsInvalid() {
        String messageError = "City should be valid";
        doReturn(messageError).when(validationAddressMessageConfig).getCityInvalid();

        AddressDto addressDto = buildAddressDtoForValidation();
        addressDto.setCity("A".repeat(256));

        Errors errors = new BeanPropertyBindingResult(addressDto, "target");

        addressDtoValidator.validate(addressDto, errors, Set.of(ValidatorHint.FOR_UPDATE));

        assertErrorPropertyDtoAdded(AddressDto.Fields.city, messageError, ErrorPropertyDto.fromErrors(errors));
    }

    @Test
    void validateForUpdate_shouldAddErrorPropertyDto_whenStateIsInvalid() {
        String messageError = "State should be valid";
        doReturn(messageError).when(validationAddressMessageConfig).getStateInvalid();

        AddressDto addressDto = buildAddressDtoForValidation();
        addressDto.setState("A".repeat(256));

        Errors errors = new BeanPropertyBindingResult(addressDto, "target");

        addressDtoValidator.validate(addressDto, errors, Set.of(ValidatorHint.FOR_UPDATE));

        assertErrorPropertyDtoAdded(AddressDto.Fields.state, messageError, ErrorPropertyDto.fromErrors(errors));
    }

    @Test
    void validateForUpdate_shouldAddErrorPropertyDto_whenCountryIsInvalid() {
        String messageError = "Country should be valid";
        doReturn(messageError).when(validationAddressMessageConfig).getCountryInvalid();

        AddressDto addressDto = buildAddressDtoForValidation();
        addressDto.setCountry("A".repeat(256));

        Errors errors = new BeanPropertyBindingResult(addressDto, "target");

        addressDtoValidator.validate(addressDto, errors, Set.of(ValidatorHint.FOR_UPDATE));

        assertErrorPropertyDtoAdded(AddressDto.Fields.country, messageError, ErrorPropertyDto.fromErrors(errors));
    }

    @Test
    void validateForUpdate_shouldAddErrorPropertyDto_whenZipCodeIsInvalid() {
        String messageError = "Zip code should be valid";
        doReturn(messageError).when(validationAddressMessageConfig).getZipCodeInvalid();

        AddressDto addressDto = buildAddressDtoForValidation();
        addressDto.setZipCode("123456");

        Errors errors = new BeanPropertyBindingResult(addressDto, "target");

        addressDtoValidator.validate(addressDto, errors, Set.of(ValidatorHint.FOR_UPDATE));

        assertErrorPropertyDtoAdded(AddressDto.Fields.zipCode, messageError, ErrorPropertyDto.fromErrors(errors));
    }

    @Test
    void validateForUpdate_shouldAddErrorPropertyDto_whenIdIsNotNull() {
        String messageError = "Id is not required";
        doReturn(messageError).when(validationAddressMessageConfig).getIdNotRequired();

        AddressDto addressDto = buildAddressDtoForValidation();
        addressDto.setId(UUID.randomUUID());

        Errors errors = new BeanPropertyBindingResult(addressDto, "target");

        addressDtoValidator.validate(addressDto, errors, Set.of(ValidatorHint.FOR_UPDATE));

        assertErrorPropertyDtoAdded(AddressDto.Fields.id, messageError, ErrorPropertyDto.fromErrors(errors));
    }
}
