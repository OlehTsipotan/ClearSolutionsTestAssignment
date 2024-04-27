package com.clearsolutions.testassignment.service.converter.user;

import com.clearsolutions.testassignment.model.dto.UserDto;
import com.clearsolutions.testassignment.model.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.clearsolutions.testassignment.testdata.factory.user.UserDataFactory.buildUserDto;
import static com.clearsolutions.testassignment.testdata.factory.user.UserDataFactory.buildUserEntityFromUpdateConverter;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class UserDtoToUserEntityUpdateConverterTest {

    @InjectMocks
    private UserDtoToUserEntityUpdateConverter converter;

    @Test
    void update_shouldUpdateEntity() {
        UserDto userDto = buildUserDto();
        UserEntity actualUserEntity = new UserEntity();

        UserEntity expectedUserEntity = buildUserEntityFromUpdateConverter();

        converter.convert(userDto, actualUserEntity);

        assertEquals(expectedUserEntity, actualUserEntity);
    }

}
