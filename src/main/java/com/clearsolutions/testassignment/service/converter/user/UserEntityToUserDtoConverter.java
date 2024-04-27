package com.clearsolutions.testassignment.service.converter.user;

import com.clearsolutions.testassignment.model.dto.UserDto;
import com.clearsolutions.testassignment.model.entity.UserEntity;
import com.clearsolutions.testassignment.service.core.converter.CreateConverter;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
public class UserEntityToUserDtoConverter implements CreateConverter<UserEntity, UserDto> {

    @Override
    public UserDto convert(UserEntity source) {
        return InternalConverter.INSTANCE.convert(source);
    }

    @Mapper
    interface InternalConverter {

        InternalConverter INSTANCE = Mappers.getMapper(InternalConverter.class);

        UserDto convert(UserEntity source);

    }

}
