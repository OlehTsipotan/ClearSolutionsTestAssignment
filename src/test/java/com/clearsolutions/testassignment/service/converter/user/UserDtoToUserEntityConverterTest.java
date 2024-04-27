package com.clearsolutions.testassignment.service.converter.user;

import com.clearsolutions.testassignment.model.dto.UserDto;
import com.clearsolutions.testassignment.model.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.clearsolutions.testassignment.testdata.factory.user.UserDataFactory.buildUserDto;
import static com.clearsolutions.testassignment.testdata.factory.user.UserDataFactory.buildUserEntityFromConverter;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class UserDtoToUserEntityConverterTest {

    @InjectMocks
    private UserDtoToUserEntityConverter converter;

    @Test
    void convert_shouldConvertDtoToEntity() {
        UserDto userDto = buildUserDto();

        UserEntity actualUserEntity = converter.convert(userDto);

        UserEntity expectedUserEntity = buildUserEntityFromConverter();

        assertEquals(expectedUserEntity, actualUserEntity);
    }
}
