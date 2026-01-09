package com.rohith.userservice.controller;

import com.rohith.userservice.Dto.UserRequestDto;
import com.rohith.userservice.Dto.UserResponseDto;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserController {

    ResponseEntity<List<UserResponseDto>> getAllUsers();

    ResponseEntity<UserResponseDto> getUserById(int id);

    ResponseEntity<UserResponseDto> createUser(UserRequestDto userRequestDto);

    ResponseEntity<UserResponseDto> deleteUser(int id );

    ResponseEntity<UserResponseDto> updateUser(UserRequestDto userRequestDto);

}
