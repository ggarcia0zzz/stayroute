package com.stayroute.backend.user.dto;

import com.stayroute.backend.user.Role;

public record UserResponse(Long id,
                           String firstName,
                           String lastName,
                           String email,
                           String phone,
                           Role role) {
}
