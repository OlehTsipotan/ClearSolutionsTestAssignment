package com.clearsolutions.testassignment.service.user;

import com.clearsolutions.testassignment.exception.EntityNotFoundException;
import com.clearsolutions.testassignment.model.dto.UserDto;
import com.clearsolutions.testassignment.model.dto.request.UserSearchDto;
import com.clearsolutions.testassignment.model.dto.response.SearchResponseDto;
import com.clearsolutions.testassignment.model.dto.rest.ErrorCode;
import com.clearsolutions.testassignment.model.entity.AddressEntity;
import com.clearsolutions.testassignment.model.entity.UserEntity;
import com.clearsolutions.testassignment.repository.jpa.specification.user.UserSpecification;
import com.clearsolutions.testassignment.repository.jpa.user.UserJpaRepository;
import com.clearsolutions.testassignment.service.address.AddressService;
import com.clearsolutions.testassignment.service.core.converter.ConverterService;
import com.clearsolutions.testassignment.service.core.localization.LocalizationService;
import com.clearsolutions.testassignment.service.core.validator.ValidatorHint;
import com.clearsolutions.testassignment.service.core.validator.ValidatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceDefault implements UserService {

    private static final String USER_NOT_FOUND = "user.not-found";

    private final UserJpaRepository userJpaRepository;

    private final ValidatorService validatorService;

    private final ConverterService converterService;

    private final AddressService addressService;

    private final LocalizationService localizationService;

    @Override
    public SearchResponseDto<UserDto> findAll(UserSearchDto userSearchDto) {
        validatorService.validate(userSearchDto);

        Specification<UserEntity> specification =
                UserSpecification.byDateOfBirthRange(userSearchDto.getDateOfBirthFrom(),
                        userSearchDto.getDateOfBirthTo());

        List<UserEntity> userEntities = userJpaRepository.findAll(specification);
        List<UserDto> userDtos =
                userEntities.stream().map(userEntity -> converterService.convert(userEntity, UserDto.class)).toList();

        return SearchResponseDto.<UserDto>builder().size(userDtos.size()).content(userDtos).build();
    }

    @Override
    public UserEntity create(UserDto userDto) {
        validatorService.validate(userDto, ValidatorHint.FOR_CREATE);

        UserEntity userEntity = converterService.convert(userDto, UserEntity.class);

        AddressEntity addressEntity = addressService.create(userDto.getAddress());
        userEntity.setAddress(addressEntity);

        return userJpaRepository.save(userEntity);
    }

    @Override
    public void deleteById(UUID id) {
        UserEntity userEntity = userJpaRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(ErrorCode.USER_NOT_FOUND,
                        localizationService.get(USER_NOT_FOUND)));

        userJpaRepository.delete(userEntity);

        addressService.deleteById(userEntity.getAddress().getId());
    }

    @Override
    public void update(UUID id, UserDto userDto) {
        validatorService.validate(userDto, ValidatorHint.FOR_UPDATE);

        UserEntity userEntity = userJpaRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(ErrorCode.USER_NOT_FOUND,
                        localizationService.get(USER_NOT_FOUND)));

        converterService.convert(userDto, userEntity);

        userJpaRepository.save(userEntity);
    }

}
