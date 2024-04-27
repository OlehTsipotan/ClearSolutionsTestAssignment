package com.clearsolutions.testassignment.service.converter.user;

import com.clearsolutions.testassignment.model.dto.UserDto;
import com.clearsolutions.testassignment.model.entity.UserEntity;
import com.clearsolutions.testassignment.service.core.converter.CreateConverter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
public class UserDtoToUserEntityConverter implements CreateConverter<UserDto, UserEntity> {

    @Override
    public UserEntity convert(UserDto source) {
        return InternalConverter.INSTANCE.convert(source);
    }

    @Mapper
    interface InternalConverter {

        InternalConverter INSTANCE = Mappers.getMapper(InternalConverter.class);

        @Mapping(target = "address", ignore = true)
        UserEntity convert(UserDto source);

    }
}
