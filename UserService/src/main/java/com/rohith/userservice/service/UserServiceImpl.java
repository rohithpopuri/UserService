package com.rohith.userservice.service;

import com.rohith.userservice.Dto.UserRequestDto;
import com.rohith.userservice.Dto.UserResponseDto;
import com.rohith.userservice.entity.User;
import com.rohith.userservice.exception.UserServiceException;
import com.rohith.userservice.mapper.UserMapper;
import com.rohith.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@RequiredArgsConstructor
@Service
@Slf4j
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository ;

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder ;



    @Override
    public List<UserResponseDto> getAllUsers() {

        List<User> users = userRepository.findAll();

        return userMapper.listOfUserToResponseDto(users);
    }

    @Override
    public UserResponseDto getUserById(int id) {

        User user = userRepository.findById(id).orElseThrow(()-> new UserServiceException("id not found", HttpStatusCode.valueOf(404)));
        return userMapper.userToUserResponseDto(user);
    }

    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto) {

        User user = userMapper.userRequestDToToUser(userRequestDto);
        log.info(String.valueOf(userRequestDto.id()));
        user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));


        User user_new= userRepository.save(user);

        return userMapper.userToUserResponseDto(user_new);
    }

    @Override
    public UserResponseDto deleteUserById(int id) {

        User user = userRepository.findById(id).orElseThrow(()-> new UserServiceException("id not found", HttpStatusCode.valueOf(404)));

        userRepository.delete(user);

        log.info("user deleted with id{}", user.getId());

        return userMapper.userToUserResponseDto(user);
    }

    @Override
    public UserResponseDto updateUser(UserRequestDto userRequestDto) {

        User user_old = userRepository.findById(userRequestDto.id()).orElseThrow(()-> new UserServiceException("id not found", HttpStatusCode.valueOf(404)));
        User user_new= userMapper.partialUserRequestDtoToUser(userRequestDto,user_old);

        userRepository.save(user_new);

        return userMapper.userToUserResponseDto(user_new) ;
    }


    public UserResponseDto getUserByName(String name){
        User user = userRepository.findByName(name)
                .orElseThrow(() ->
                        new UserServiceException(
                                "User not found with name " + name,
                                HttpStatus.NOT_FOUND));

        return userMapper.userToUserResponseDto(user);
    }
}
