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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.clearsolutions.testassignment.testdata.factory.address.AddressDataFactory.buildAddressDtoFromConverter;
import static com.clearsolutions.testassignment.testdata.factory.address.AddressDataFactory.buildAddressEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AddressServiceDefaultTest {

    @Mock
    private AddressJpaRepository addressJpaRepository;

    @Mock
    private ConverterService converterService;

    @Mock
    private ValidatorService validatorService;

    @Mock
    private LocalizationService localizationService;

    @InjectMocks
    private AddressServiceDefault addressServiceDefault;

    @Test
    void findAll_shouldReturnSearchResponseDtoWithAddressDtos_whenAddressSearchDtoIsValid() {

        AddressEntity addressEntity = buildAddressEntity();
        List<AddressEntity> addressEntities = List.of(addressEntity);
        AddressDto addressDto = buildAddressDtoFromConverter();

        when(addressJpaRepository.findAll()).thenReturn(addressEntities);
        when(converterService.convert(addressEntity, AddressDto.class)).thenReturn(addressDto);

        SearchResponseDto<AddressDto> actualSearchResponseDto = addressServiceDefault.findAll();

        SearchResponseDto<AddressDto> expectedSearchResponseDto = SearchResponseDto.<AddressDto>builder()
                .size(1)
                .content(List.of(addressDto))
                .build();

        assertEquals(expectedSearchResponseDto, actualSearchResponseDto);
    }

    @Test
    void create_shouldReturnAddressEntity_whenAddressDtoIsValid() {
        AddressDto addressDto = buildAddressDtoFromConverter();
        AddressEntity addressEntity = buildAddressEntity();

        when(converterService.convert(addressDto, AddressEntity.class)).thenReturn(addressEntity);
        when(addressJpaRepository.save(addressEntity)).thenReturn(addressEntity);

        AddressEntity actualAddressEntity = addressServiceDefault.create(addressDto);

        assertEquals(addressEntity, actualAddressEntity);
    }

    @Test
    void deleteById_shouldDeleteAddressEntity_whenAddressEntityExists() {
        AddressEntity addressEntity = buildAddressEntity();

        when(addressJpaRepository.findById(addressEntity.getId())).thenReturn(java.util.Optional.of(addressEntity));

        addressServiceDefault.deleteById(addressEntity.getId());

        verify(addressJpaRepository).delete(addressEntity);
    }

    @Test
    void deleteById_shouldThrowEntityNotFoundException_whenAddressEntityDoesNotExist() {
        AddressEntity addressEntity = buildAddressEntity();

        when(addressJpaRepository.findById(addressEntity.getId())).thenReturn(java.util.Optional.empty());

        EntityNotFoundException actual = assertThrows(EntityNotFoundException.class, () -> addressServiceDefault.deleteById(addressEntity.getId()));

        assertEquals(ErrorCode.ADDRESS_NOT_FOUND, actual.getErrorCode());
    }

    @Test
    void update_shouldUpdateAddressEntity_whenAddressEntityExists() {
        AddressEntity addressEntity = buildAddressEntity();
        AddressDto addressDto = buildAddressDtoFromConverter();

        when(addressJpaRepository.findById(addressEntity.getId())).thenReturn(java.util.Optional.of(addressEntity));

        addressServiceDefault.update(addressEntity.getId(), addressDto);

        verify(addressJpaRepository).save(addressEntity);
        verify(converterService).convert(addressDto, addressEntity);
    }

    @Test
    void update_shouldThrowEntityNotFoundException_whenAddressEntityDoesNotExist() {
        AddressEntity addressEntity = buildAddressEntity();
        AddressDto addressDto = buildAddressDtoFromConverter();

        when(addressJpaRepository.findById(addressEntity.getId())).thenReturn(java.util.Optional.empty());

        EntityNotFoundException actual = assertThrows(EntityNotFoundException.class, () -> addressServiceDefault.update(addressEntity.getId(), addressDto));

        assertEquals(ErrorCode.ADDRESS_NOT_FOUND, actual.getErrorCode());
    }

}
