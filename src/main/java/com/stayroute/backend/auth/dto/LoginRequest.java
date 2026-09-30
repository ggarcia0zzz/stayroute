package com.stayroute.backend.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest (
        @NotBlank(message = "This field can not be empty")
        @Email(message = "Invalid email format")
        String email,

        @NotBlank(message = "This field can not be empty")
        String password
) {
}
