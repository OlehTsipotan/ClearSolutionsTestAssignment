package com.clearsolutions.testassignment.testdata.factory.address;

import com.clearsolutions.testassignment.model.dto.AddressDto;
import com.clearsolutions.testassignment.model.entity.AddressEntity;

import java.util.UUID;

public class AddressDataFactory {

    private static final UUID ID = UUID.randomUUID();

    private static final String CITY = "city";

    private static final String COUNTRY = "country";

    private static final String STREET = "street";

    private static final String STATE = "state";

    private static final String ZIP_CODE = "12345";

    private AddressDataFactory() {
    }

    public static AddressDto buildAddressDto() {
        return AddressDto.builder()
                .id(ID)
                .street(STREET)
                .city(CITY)
                .state(STATE)
                .country(COUNTRY)
                .zipCode(ZIP_CODE)
                .build();
    }

    public static AddressDto buildAddressDtoForValidation() {
        return AddressDto.builder()
                .street(STREET)
                .city(CITY)
                .state(STATE)
                .country(COUNTRY)
                .zipCode(ZIP_CODE)
                .build();
    }

    public static AddressEntity buildAddressEntityFromConverter() {
        return AddressEntity.builder()
                .id(ID)
                .street(STREET)
                .city(CITY)
                .state(STATE)
                .country(COUNTRY)
                .zipCode(ZIP_CODE)
                .build();
    }

    public static AddressDto buildAddressDtoFromConverter() {
        return AddressDto.builder()
                .id(ID)
                .street(STREET)
                .city(CITY)
                .state(STATE)
                .country(COUNTRY)
                .zipCode(ZIP_CODE)
                .build();
    }

    public static AddressEntity buildAddressEntity() {
        return AddressEntity.builder()
                .id(ID)
                .street(STREET)
                .city(CITY)
                .state(STATE)
                .country(COUNTRY)
                .zipCode(ZIP_CODE)
                .build();
    }

    public static AddressEntity buildAddressEntityFromUpdateConverter() {
        return AddressEntity.builder()
                .street(STREET)
                .city(CITY)
                .state(STATE)
                .country(COUNTRY)
                .zipCode(ZIP_CODE)
                .build();
    }

}
