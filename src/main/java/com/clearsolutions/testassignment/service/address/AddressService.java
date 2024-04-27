package com.clearsolutions.testassignment.service.address;

import com.clearsolutions.testassignment.model.dto.AddressDto;
import com.clearsolutions.testassignment.model.dto.response.SearchResponseDto;
import com.clearsolutions.testassignment.model.entity.AddressEntity;

import java.util.UUID;

public interface AddressService {

    SearchResponseDto<AddressDto> findAll();

    AddressEntity create(AddressDto addressDto);

    void deleteById(UUID id);

    void update(UUID id, AddressDto address);
}
