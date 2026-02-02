package com.rohith.userservice.service;

import com.rohith.userservice.Dto.LoginRequest;
import com.rohith.userservice.Dto.LoginResponseDto;
import com.rohith.userservice.Dto.UserRequestDto;
import com.rohith.userservice.Dto.UserResponseDto;
import com.rohith.userservice.Security.CustomUserDetails;
import com.rohith.userservice.entity.User;
import com.rohith.userservice.mapper.UserMapper;
import com.rohith.userservice.repository.UserRepository;
import com.rohith.userservice.util.AuthUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    private final AuthUtils authUtils;
    @Override
    public LoginResponseDto login(LoginRequest loginRequest) {


        Authentication authentication = authenticationManager.
                authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));


        CustomUserDetails user= (CustomUserDetails) authentication.getPrincipal();

        String token = authUtils.generateToken(user);

        return new LoginResponseDto(token, user.getId());

    }

    @Override
    public UserResponseDto signUp(UserRequestDto userRequestDto) {
        Optional<User> user = userRepository
                .findByEmail(userRequestDto.email());

        if(user.isPresent()) throw new BadCredentialsException("Cannot signup, User already exists with email "+userRequestDto.email());

        // Map SignupDto to UserEntity and encode the password

        User user1=userMapper.userRequestDToToUser(userRequestDto);
        user1.setPasswordHash(passwordEncoder.encode(user1.getPasswordHash()));


        // Save the user entity to the database
        User savedUser = userRepository.save(user1);
        return userMapper.userToUserResponseDto(savedUser);
    }
}
