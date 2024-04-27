package com.clearsolutions.testassignment.testdata.factory.user;

import com.clearsolutions.testassignment.model.dto.UserDto;
import com.clearsolutions.testassignment.model.entity.UserEntity;

import java.time.LocalDate;
import java.util.UUID;

import static com.clearsolutions.testassignment.testdata.factory.address.AddressDataFactory.buildAddressDto;
import static com.clearsolutions.testassignment.testdata.factory.address.AddressDataFactory.buildAddressDtoFromConverter;
import static com.clearsolutions.testassignment.testdata.factory.address.AddressDataFactory.buildAddressEntity;
import static com.clearsolutions.testassignment.testdata.factory.address.AddressDataFactory.buildAddressEntityFromConverter;

public class UserDataFactory {

    private static final UUID ID = UUID.randomUUID();

    private static final String EMAIL = "myemail@example.com";

    private static final String FIRST_NAME = "John";

    private static final String LAST_NAME = "Doe";

    private static final LocalDate DATE_OF_BIRTH = LocalDate.of(1999, 1, 1);

    private static final String PHONE_NUMBER = "1234567890";

    private UserDataFactory() {
    }

    public static UserDto buildUserDtoFromConverter() {
        return UserDto.builder()
                .id(ID)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .dateOfBirth(DATE_OF_BIRTH)
                .email(EMAIL)
                .phoneNumber(PHONE_NUMBER)
                .address(buildAddressDtoFromConverter()).build();
    }

    public static UserDto buildUserDto() {
        return UserDto.builder()
                .id(ID)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .dateOfBirth(DATE_OF_BIRTH)
                .email(EMAIL)
                .phoneNumber(PHONE_NUMBER)
                .address(buildAddressDto()).build();
    }

    public static UserDto buildUserDtoForValidation() {
        return UserDto.builder()
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .dateOfBirth(DATE_OF_BIRTH)
                .email(EMAIL)
                .phoneNumber(PHONE_NUMBER)
                .address(buildAddressDto()).build();
    }

    public static UserEntity buildUserEntityFromConverter() {
        return UserEntity.builder()
                .id(ID)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .dateOfBirth(DATE_OF_BIRTH)
                .email(EMAIL)
                .phoneNumber(PHONE_NUMBER).build();
    }

    public static UserEntity buildUserEntity() {
        return UserEntity.builder()
                .id(ID)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .dateOfBirth(DATE_OF_BIRTH)
                .email(EMAIL)
                .phoneNumber(PHONE_NUMBER)
                .address(buildAddressEntity())
                .build();
    }

    public static UserEntity buildUserEntityFromUpdateConverter() {
        return UserEntity.builder()
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .dateOfBirth(DATE_OF_BIRTH)
                .email(EMAIL)
                .phoneNumber(PHONE_NUMBER)
                .address(buildAddressEntityFromConverter())
                .build();
    }
}
