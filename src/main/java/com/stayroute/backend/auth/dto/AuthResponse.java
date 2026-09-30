package com.stayroute.backend.auth.dto;

import com.stayroute.backend.user.Role;

public record AuthResponse(String token,
                           String tokenType,
                           String email,
                           Role role) {
    public static AuthResponse of(String token, String email, Role role) {
        return new AuthResponse(token, "Bearer", email, role);
    }
}
