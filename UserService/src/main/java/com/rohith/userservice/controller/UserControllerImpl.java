package com.rohith.userservice.controller;

import com.rohith.userservice.Dto.UserRequestDto;
import com.rohith.userservice.Dto.UserResponseDto;
import com.rohith.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserControllerImpl implements UserController {

    private final UserService userService ;

    @GetMapping("/getallusers")
    @Override
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        List<UserResponseDto> userResponseDtos = userService.getAllUsers();
        return  ResponseEntity.ok(userResponseDtos);
    }
    @GetMapping("/getuser/{id}")
    @Override
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable int id) {
        UserResponseDto userResponseDto =userService.getUserById(id);

        return ResponseEntity.ok(userResponseDto);
    }
    @PostMapping("/createuser")
    @Override
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto userRequestDto) {

        UserResponseDto userResponseDto = userService.createUser(userRequestDto);

        return ResponseEntity.ok(userResponseDto);
    }

    @DeleteMapping("/deleteuser")
    @Override
    public ResponseEntity<UserResponseDto> deleteUser(int id) {
        UserResponseDto userResponseDto=userService.deleteUserById(id);

        return ResponseEntity.ok(userResponseDto);
    }
    @PutMapping("/updateuser")
    @Override
    public ResponseEntity<UserResponseDto> updateUser(UserRequestDto userRequestDto) {

        return null;
    }

}

