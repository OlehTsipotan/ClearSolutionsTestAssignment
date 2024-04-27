package com.clearsolutions.testassignment.service.converter.user;

import com.clearsolutions.testassignment.model.dto.UserDto;
import com.clearsolutions.testassignment.model.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.clearsolutions.testassignment.testdata.factory.user.UserDataFactory.buildUserDtoFromConverter;
import static com.clearsolutions.testassignment.testdata.factory.user.UserDataFactory.buildUserEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class UserEntityToUserDtoConverterTest {

    @InjectMocks
    private UserEntityToUserDtoConverter converter;

    @Test
    void convert_shouldConvertDtoToEntity() {
        UserEntity userEntity = buildUserEntity();

        UserDto actualUserDto = converter.convert(userEntity);

        UserDto expectedUserDto = buildUserDtoFromConverter();

        assertEquals(expectedUserDto, actualUserDto);
    }
}
