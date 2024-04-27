package com.clearsolutions.testassignment.service.converter.user;

import com.clearsolutions.testassignment.model.dto.UserDto;
import com.clearsolutions.testassignment.model.entity.UserEntity;
import com.clearsolutions.testassignment.service.core.converter.UpdateConverter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
public class UserDtoToUserEntityUpdateConverter implements UpdateConverter<UserDto, UserEntity> {

    @Override
    public void convert(UserDto source, UserEntity target) {
        InternalConverter.INSTANCE.convert(source, target);
    }

    @Mapper
    interface InternalConverter {

        InternalConverter INSTANCE = Mappers.getMapper(InternalConverter.class);

        @Mapping(target = "id", ignore = true)
        void convert(UserDto source, @MappingTarget UserEntity target);

    }

}
