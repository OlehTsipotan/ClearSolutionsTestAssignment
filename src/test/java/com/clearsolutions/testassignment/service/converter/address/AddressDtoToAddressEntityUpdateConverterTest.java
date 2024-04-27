package com.clearsolutions.testassignment.service.converter.address;

import com.clearsolutions.testassignment.model.dto.AddressDto;
import com.clearsolutions.testassignment.model.entity.AddressEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.clearsolutions.testassignment.testdata.factory.address.AddressDataFactory.buildAddressDto;
import static com.clearsolutions.testassignment.testdata.factory.address.AddressDataFactory.buildAddressEntityFromUpdateConverter;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class AddressDtoToAddressEntityUpdateConverterTest {

    @InjectMocks
    private AddressDtoToAddressEntityUpdateConverter converter;

    @Test
    void update_shouldUpdateEntity() {
        AddressDto addressDto = buildAddressDto();
        AddressEntity actualAddressEntity = new AddressEntity();

        AddressEntity expectedAddressEntity = buildAddressEntityFromUpdateConverter();

        converter.convert(addressDto, actualAddressEntity);

        assertEquals(expectedAddressEntity, actualAddressEntity);
    }

}
