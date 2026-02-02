package com.rohith.userservice.controller;

import com.rohith.userservice.Dto.CreateValidation;
import com.rohith.userservice.Dto.UpdateValidation;
import com.rohith.userservice.Dto.UserRequestDto;
import com.rohith.userservice.Dto.UserResponseDto;
import com.rohith.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserControllerImpl implements UserController {

    private final UserService userService ;

    @GetMapping("/public/getallusers")
    @Override
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        List<UserResponseDto> userResponseDtos = userService.getAllUsers();
        return  ResponseEntity.ok(userResponseDtos);
    }
    @GetMapping("/public/getuser/{id}")
    @Override
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable int id) {
        UserResponseDto userResponseDto =userService.getUserById(id);

        return ResponseEntity.ok(userResponseDto);
    }
    @PostMapping("/public/createuser")
    @Override
    public ResponseEntity<UserResponseDto> createUser(@Validated(CreateValidation.class) @RequestBody UserRequestDto userRequestDto) {

        UserResponseDto userResponseDto = userService.createUser(userRequestDto);

        return ResponseEntity.ok(userResponseDto);
    }

    @DeleteMapping("/admin/deleteuser/{id}")
    @Override
    public ResponseEntity<UserResponseDto> deleteUser(@PathVariable int id) {
        UserResponseDto userResponseDto=userService.deleteUserById(id);

        return ResponseEntity.ok(userResponseDto);
    }
    @PutMapping("/public/updateuser")
    @Override
    public ResponseEntity<UserResponseDto> updateUser(@Validated(UpdateValidation.class)@RequestBody UserRequestDto userRequestDto) {

        return ResponseEntity.ok(userService.updateUser(userRequestDto));
    }

    @GetMapping("/public/getuserbyname")
    @Override
    public ResponseEntity<UserResponseDto> getUserByName(@PathVariable String name) {
        return ResponseEntity.ok(userService.getUserByName(name));
    }


}

