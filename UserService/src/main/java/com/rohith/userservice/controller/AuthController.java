package com.rohith.userservice.controller;

import com.rohith.userservice.Dto.LoginRequest;
import com.rohith.userservice.Dto.LoginResponseDto;
import com.rohith.userservice.Dto.UserRequestDto;
import com.rohith.userservice.Dto.UserResponseDto;
import org.springframework.http.ResponseEntity;

public interface AuthController {

    ResponseEntity<LoginResponseDto> login(LoginRequest loginRequest);

    ResponseEntity<UserResponseDto> singUp(UserRequestDto userRequestDto);




}
