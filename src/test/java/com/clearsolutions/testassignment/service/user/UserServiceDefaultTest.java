package com.clearsolutions.testassignment.service.user;

import com.clearsolutions.testassignment.exception.EntityNotFoundException;
import com.clearsolutions.testassignment.model.dto.UserDto;
import com.clearsolutions.testassignment.model.dto.request.UserSearchDto;
import com.clearsolutions.testassignment.model.dto.response.SearchResponseDto;
import com.clearsolutions.testassignment.model.dto.rest.ErrorCode;
import com.clearsolutions.testassignment.model.entity.UserEntity;
import com.clearsolutions.testassignment.repository.jpa.user.UserJpaRepository;
import com.clearsolutions.testassignment.service.address.AddressService;
import com.clearsolutions.testassignment.service.core.converter.ConverterService;
import com.clearsolutions.testassignment.service.core.localization.LocalizationService;
import com.clearsolutions.testassignment.service.core.validator.ValidatorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.UUID;

import static com.clearsolutions.testassignment.testdata.factory.user.UserDataFactory.buildUserDto;
import static com.clearsolutions.testassignment.testdata.factory.user.UserDataFactory.buildUserDtoFromConverter;
import static com.clearsolutions.testassignment.testdata.factory.user.UserDataFactory.buildUserEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceDefaultTest {

    @Mock
    private UserJpaRepository userJpaRepository;

    @Mock
    private ValidatorService validatorService;

    @Mock
    private ConverterService converterService;

    @Mock
    private AddressService addressService;

    @Mock
    private LocalizationService localizationService;

    @InjectMocks
    private UserServiceDefault userServiceDefault;

    @Test
    void findAll_shouldReturnSearchResponseDtoWithUserDtos_whenUserSearchDtoIsValid() {
        UserSearchDto givenUserSearchDto = new UserSearchDto();

        UserEntity userEntity = buildUserEntity();
        List<UserEntity> userEntities = List.of(userEntity);
        UserDto userDto = buildUserDtoFromConverter();

        when(userJpaRepository.findAll(any(Specification.class))).thenReturn(userEntities);
        when(converterService.convert(userEntity, UserDto.class)).thenReturn(userDto);

        SearchResponseDto<UserDto> actualSearchResponseDto = userServiceDefault.findAll(givenUserSearchDto);

        SearchResponseDto<UserDto> expectedSearchResponseDto =
                SearchResponseDto.<UserDto>builder().size(1).content(List.of(userDto)).build();

        assertEquals(expectedSearchResponseDto, actualSearchResponseDto);
    }

    @Test
    void create_whenUserDtoIsValid_shouldReturnUserEntity() {
        UserDto givenUserDto = buildUserDtoFromConverter();
        UserEntity expectedUserEntity = buildUserEntity();

        when(converterService.convert(givenUserDto, UserEntity.class)).thenReturn(expectedUserEntity);
        when(addressService.create(givenUserDto.getAddress())).thenReturn(expectedUserEntity.getAddress());
        when(userJpaRepository.save(expectedUserEntity)).thenReturn(expectedUserEntity);

        UserEntity actualUserEntity = userServiceDefault.create(givenUserDto);

        assertEquals(expectedUserEntity, actualUserEntity);
    }

    @Test
    void deleteById_shouldDeleteUserEntityAndAddressEntity_whenUserEntityExists() {
        UserEntity userEntity = buildUserEntity();
        UUID givenId = userEntity.getId();


        when(userJpaRepository.findById(givenId)).thenReturn(java.util.Optional.of(userEntity));

        userServiceDefault.deleteById(givenId);

        verify(userJpaRepository).delete(userEntity);
        verify(addressService).deleteById(userEntity.getAddress().getId());
    }

    @Test
    void deleteById_shouldThrowEntityNotFoundException_whenUserEntityDoesNotExist() {
        UUID givenId = UUID.randomUUID();

        when(userJpaRepository.findById(givenId)).thenReturn(java.util.Optional.empty());

        EntityNotFoundException actual =
                assertThrows(EntityNotFoundException.class, () -> userServiceDefault.deleteById(givenId));

        assertEquals(ErrorCode.USER_NOT_FOUND, actual.getErrorCode());
    }

    @Test
    void update_shouldUpdateUserEntity_whenUserEntityExists() {
        UserDto givenUserDto = buildUserDto();
        UUID givenId = givenUserDto.getId();

        UserEntity userEntity = buildUserEntity();
        when(userJpaRepository.findById(givenId)).thenReturn(java.util.Optional.of(userEntity));

        userServiceDefault.update(givenId, givenUserDto);

        verify(userJpaRepository).save(userEntity);
        verify(converterService).convert(givenUserDto, userEntity);
    }

    @Test
    void update_shouldThrowEntityNotFoundException_whenUserEntityDoesNotExist() {
        UserDto givenUserDto = buildUserDto();
        UUID givenId = givenUserDto.getId();

        when(userJpaRepository.findById(givenId)).thenReturn(java.util.Optional.empty());

        EntityNotFoundException actual =
                assertThrows(EntityNotFoundException.class, () -> userServiceDefault.update(givenId, givenUserDto));

        assertEquals(ErrorCode.USER_NOT_FOUND, actual.getErrorCode());
    }

}
