package com.rohith.userservice.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginRequest {
    @NotBlank(message = "user canot be null")
    private String username;

    @NotBlank(message = "password cannot be null")
    private String password;

}
