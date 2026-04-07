package com.ardent.auth_service.dto;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginRequestDTO {

    @NotNull(message = "Email Can't be empty")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password Can't be empty")
    @Size(min = 8 , message = "Password must be 8 character long.")
    private String password;
}
