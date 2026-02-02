package com.rohith.userservice.service;

import com.rohith.userservice.Dto.LoginRequest;
import com.rohith.userservice.Dto.LoginResponseDto;
import com.rohith.userservice.Dto.UserRequestDto;
import com.rohith.userservice.Dto.UserResponseDto;

public interface AuthService {

    LoginResponseDto login(LoginRequest loginRequest);
    UserResponseDto signUp(UserRequestDto userRequestDto);
}
