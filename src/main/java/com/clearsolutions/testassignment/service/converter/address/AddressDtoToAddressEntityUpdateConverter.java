package com.clearsolutions.testassignment.service.converter.address;

import com.clearsolutions.testassignment.model.dto.AddressDto;
import com.clearsolutions.testassignment.model.entity.AddressEntity;
import com.clearsolutions.testassignment.service.core.converter.UpdateConverter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
public class AddressDtoToAddressEntityUpdateConverter implements UpdateConverter<AddressDto, AddressEntity> {

    @Override
    public void convert(AddressDto source, AddressEntity target) {
        InternalConverter.INSTANCE.convert(source, target);
    }

    @Mapper
    interface InternalConverter {

        InternalConverter INSTANCE = Mappers.getMapper(InternalConverter.class);

        @Mapping(target = "id", ignore = true)
        void convert(AddressDto source, @MappingTarget AddressEntity target);

    }

}
