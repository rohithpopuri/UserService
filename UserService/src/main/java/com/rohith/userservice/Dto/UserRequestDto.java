package com.rohith.userservice.Dto;

import com.rohith.userservice.entity.Role;
import com.rohith.userservice.entity.Status;

public record UserRequestDto(String name , String passwordHash, String email , Role role , Status status ) {
}
