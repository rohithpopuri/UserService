package com.rohith.userservice.service;


import com.rohith.userservice.Dto.UserRequestDto;
import com.rohith.userservice.Dto.UserResponseDto;
import com.rohith.userservice.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    List<UserResponseDto> getAllUsers();
    UserResponseDto getUserById(int id);
    UserResponseDto createUser(UserRequestDto userRequestDto);
    UserResponseDto deleteUserById(int id);
    UserResponseDto updateUser(UserRequestDto userRequestDto);

}
