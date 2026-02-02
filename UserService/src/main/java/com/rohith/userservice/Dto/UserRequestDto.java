package com.rohith.userservice.Dto;

import com.rohith.userservice.entity.Role;
import com.rohith.userservice.entity.Status;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserRequestDto(@NotNull(groups = UpdateValidation.class,message = "id is null")
                             Integer id ,
                             @NotBlank(groups = CreateValidation.class,message = "name is null")
                             String name,

                             @NotBlank(groups = CreateValidation.class,message = "password0Hashnull")
                             @Size(min = 8)
                             String passwordHash,

                             @NotBlank(groups = CreateValidation.class)
                             @Email
                             String email,

                             @NotNull(groups = CreateValidation.class)
                             Role role,

                             @NotNull(groups = CreateValidation.class)
                             Status status) {
}
