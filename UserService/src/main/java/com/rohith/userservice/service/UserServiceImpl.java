package com.rohith.userservice.service;

import com.rohith.userservice.Dto.UserRequestDto;
import com.rohith.userservice.Dto.UserResponseDto;
import com.rohith.userservice.entity.User;
import com.rohith.userservice.mapper.UserMapper;
import com.rohith.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository ;

    private final UserMapper userMapper;


    @Override
    public List<UserResponseDto> getAllUsers() {

        List<User> users = userRepository.findAll();

        return userMapper.listOfUserToResponseDto(users);
    }

    @Override
    public UserResponseDto getUserById(int id) {

        User user = userRepository.findById(id).orElseThrow(()-> new RuntimeException("id not found"));
        return userMapper.userToUserResponseDto(user);
    }

    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto) {

        User user = userMapper.userRequestDToToUser(userRequestDto);

        User user_new= userRepository.save(user);

        return userMapper.userToUserResponseDto(user_new);
    }

    @Override
    public UserResponseDto deleteUserById(int id) {

        User user = userRepository.findById(id).orElseThrow(()-> new RuntimeException("id not found"));

        userRepository.deleteById(id);

        return userMapper.userToUserResponseDto(user);
    }

    @Override
    public UserResponseDto updateUser(UserRequestDto userRequestDto) {
        return null;
    }
}
