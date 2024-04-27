package com.clearsolutions.testassignment.service.converter.address;

import com.clearsolutions.testassignment.model.dto.AddressDto;
import com.clearsolutions.testassignment.model.entity.AddressEntity;
import com.clearsolutions.testassignment.service.core.converter.CreateConverter;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
public class AddressEntityToAddressDtoConverter implements CreateConverter<AddressEntity, AddressDto> {

    @Override
    public AddressDto convert(AddressEntity source) {
        return InternalConverter.INSTANCE.convert(source);
    }

    @Mapper
    interface InternalConverter {

        InternalConverter INSTANCE = Mappers.getMapper(InternalConverter.class);

        AddressDto convert(AddressEntity source);

    }

}
