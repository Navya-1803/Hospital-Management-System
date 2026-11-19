package com.example.hms.config;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SecurityConfigTest {
    @Test
    void providesPasswordEncoderThatVerifiesEncodedPassword() {
        PasswordEncoder encoder = new SecurityConfig().passwordEncoder();
        String encoded = encoder.encode("secure-pass");
        assertTrue(encoder.matches("secure-pass", encoded));
    }
}
