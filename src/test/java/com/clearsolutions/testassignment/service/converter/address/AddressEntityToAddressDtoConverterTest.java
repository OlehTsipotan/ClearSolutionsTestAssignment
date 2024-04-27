package com.clearsolutions.testassignment.service.converter.address;

import com.clearsolutions.testassignment.model.dto.AddressDto;
import com.clearsolutions.testassignment.model.entity.AddressEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.clearsolutions.testassignment.testdata.factory.address.AddressDataFactory.buildAddressDtoFromConverter;
import static com.clearsolutions.testassignment.testdata.factory.address.AddressDataFactory.buildAddressEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class AddressEntityToAddressDtoConverterTest {

    @InjectMocks
    private AddressEntityToAddressDtoConverter converter;

    @Test
    void convert_shouldConvertEntityToDto() {
        AddressEntity addressEntity = buildAddressEntity();

        AddressDto actualAddressDto = converter.convert(addressEntity);

        AddressDto expectedAddressDto = buildAddressDtoFromConverter();

        assertEquals(expectedAddressDto, actualAddressDto);
    }

}
