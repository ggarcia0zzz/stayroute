package com.stayroute.backend.auth.dto;

import com.stayroute.backend.user.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(@NotBlank(message = "This field can not be empty")
                              @Size(max=80,message = "First name can not exceed 80 characters")
                              String firstName,

                              @NotBlank(message = "This field can not be empty")
                              @Size(max=80,message = "Last name can not exceed 80 characters")
                              String lastName,

                             @NotBlank(message = "This field can not be empty")
                              @Email(message = "Invalid email format")
                              String email,

                              @NotBlank(message = "This field can not be empty")
                              @Size(min=8,message = "Password must be at least 8 characters long")
                              String password,

                              @Size(max=30,message = "Phone number can not exceed 30 characters")
                              String phone,

                              Role role) {
}
