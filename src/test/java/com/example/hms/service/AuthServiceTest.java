package com.example.hms.service;

import com.example.hms.config.JwtService;
import com.example.hms.dto.AuthRequest;
import com.example.hms.dto.AuthResponse;
import com.example.hms.entity.User;
import com.example.hms.repository.UserRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AuthServiceTest {
    private final UserRepository users = mock(UserRepository.class);
    private final PasswordEncoder encoder = mock(PasswordEncoder.class);
    private final JwtService jwt = mock(JwtService.class);
    private final AuthService service = new AuthService(users, encoder, jwt);

    @Test
    void registersWithEncodedPassword() {
        AuthRequest request = request();
        when(users.existsByEmail("navya@example.com")).thenReturn(false);
        when(encoder.encode("secure-pass")).thenReturn("hashed");
        AuthResponse result = service.registerUser(request);
        assertEquals("User registered successfully", result.getMessage());
        verify(users).save(any(User.class));
    }

    @Test
    void rejectsDuplicateEmail() {
        when(users.existsByEmail("navya@example.com")).thenReturn(true);
        assertThrows(ResponseStatusException.class, () -> service.registerUser(request()));
    }

    @Test
    void logsInWithValidCredentials() {
        User user = new User(); user.setEmail("navya@example.com"); user.setPassword("hashed");
        when(users.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
        when(encoder.matches("secure-pass", "hashed")).thenReturn(true);
        when(jwt.generateToken(user)).thenReturn("token");
        assertEquals("token", service.loginUser(request()).getToken());
    }

    @Test
    void rejectsInvalidCredentials() {
        when(users.findByEmail("navya@example.com")).thenReturn(Optional.empty());
        assertThrows(ResponseStatusException.class, () -> service.loginUser(request()));
    }

    private AuthRequest request() {
        AuthRequest request = new AuthRequest(); request.setName("Navya");
        request.setEmail("navya@example.com"); request.setPassword("secure-pass"); return request;
    }
}
