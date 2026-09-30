package com.stayroute.backend.auth;

import com.stayroute.backend.auth.dto.AuthResponse;
import com.stayroute.backend.auth.dto.LoginRequest;
import com.stayroute.backend.auth.dto.RegisterRequest;
import com.stayroute.backend.common.exception.EmailAlreadyExistsException;
import com.stayroute.backend.common.exception.ResourceNotFoundException;
import com.stayroute.backend.security.JwtService;
import com.stayroute.backend.user.Role;
import com.stayroute.backend.user.User;
import com.stayroute.backend.user.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Transactional
    public AuthResponse register(RegisterRequest request) {
        String email = normalize(request.email());

        if (userRepository.existsByEmail(email)) {
            throw new EmailAlreadyExistsException(email);
        }

        User user = new User();
        user.setFirstName(request.firstName().trim());
        user.setLastName(request.lastName().trim());
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setPhone(request.phone());
        user.setRole(request.role() == Role.OWNER ? Role.OWNER : Role.GUEST);

        userRepository.save(user);
        return buildResponse(user);
    }

    @Transactional(readOnly = true)
    public AuthResponse login(LoginRequest request) {
        String email = normalize(request.email());

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, request.password()));

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado: " + email));

        return buildResponse(user);
    }

    private AuthResponse buildResponse(User user) {
        String token = jwtService.generateToken(user.getEmail(), user.getRole().name());
        return AuthResponse.of(token, user.getEmail(), user.getRole());
    }

    private String normalize(String email) {
        return email.trim().toLowerCase();
    }
}
