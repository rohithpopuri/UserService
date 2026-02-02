package com.rohith.userservice.controller;

import com.rohith.userservice.Dto.LoginRequest;
import com.rohith.userservice.Dto.LoginResponseDto;
import com.rohith.userservice.Dto.UserRequestDto;
import com.rohith.userservice.Dto.UserResponseDto;
import com.rohith.userservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController{


    private final AuthService authService;

    @PostMapping("/login")
    @Override
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }

    @PostMapping("/signup")
    @Override
    public ResponseEntity<UserResponseDto> singUp(@RequestBody UserRequestDto userRequestDto) {
        return ResponseEntity.ok(authService.signUp(userRequestDto));
    }
}
