package com.clearsolutions.testassignment.service.address;

import com.clearsolutions.testassignment.exception.EntityNotFoundException;
import com.clearsolutions.testassignment.model.dto.AddressDto;
import com.clearsolutions.testassignment.model.dto.response.SearchResponseDto;
import com.clearsolutions.testassignment.model.dto.rest.ErrorCode;
import com.clearsolutions.testassignment.model.entity.AddressEntity;
import com.clearsolutions.testassignment.repository.jpa.address.AddressJpaRepository;
import com.clearsolutions.testassignment.service.core.converter.ConverterService;
import com.clearsolutions.testassignment.service.core.localization.LocalizationService;
import com.clearsolutions.testassignment.service.core.validator.ValidatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AddressServiceDefault implements AddressService {

    private static final String ADDRESS_NOT_FOUND = "address.not-found";

    private final AddressJpaRepository addressJpaRepository;

    private final ConverterService converterService;

    private final ValidatorService validatorService;

    private final LocalizationService localizationService;

    @Override
    public SearchResponseDto<AddressDto> findAll() {
        List<AddressEntity> addressEntities = addressJpaRepository.findAll();

        List<AddressDto> addressDtos =
                addressEntities.stream().map(addressEntity -> converterService.convert(addressEntity, AddressDto.class))
                        .toList();

        return SearchResponseDto.<AddressDto>builder()
                .size(addressDtos.size())
                .content(addressDtos)
                .build();
    }

    @Override
    public AddressEntity create(AddressDto addressDto) {
        validatorService.validate(addressDto);

        AddressEntity addressEntity = converterService.convert(addressDto, AddressEntity.class);

        return addressJpaRepository.save(addressEntity);
    }

    @Override
    public void deleteById(UUID id) {
        AddressEntity addressEntity = addressJpaRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(ErrorCode.ADDRESS_NOT_FOUND,
                        localizationService.get(ADDRESS_NOT_FOUND)));

        addressJpaRepository.delete(addressEntity);
    }

    @Override
    public void update(UUID id, AddressDto addressDto) {
        validatorService.validate(addressDto);

        AddressEntity addressEntity = addressJpaRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(ErrorCode.ADDRESS_NOT_FOUND,
                        localizationService.get(ADDRESS_NOT_FOUND)));

        converterService.convert(addressDto, addressEntity);

        addressJpaRepository.save(addressEntity);
    }

}
