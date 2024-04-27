package com.clearsolutions.testassignment.service.user;

import com.clearsolutions.testassignment.model.dto.UserDto;
import com.clearsolutions.testassignment.model.dto.request.UserSearchDto;
import com.clearsolutions.testassignment.model.dto.response.SearchResponseDto;
import com.clearsolutions.testassignment.model.entity.UserEntity;

import java.util.UUID;

public interface UserService {

    SearchResponseDto<UserDto> findAll(UserSearchDto userSearchDto);

    UserEntity create(UserDto userDto);

    void deleteById(UUID id);

    void update(UUID id, UserDto userDto);
}
