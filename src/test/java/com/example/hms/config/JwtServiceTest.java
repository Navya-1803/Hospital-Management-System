package com.example.hms.config;

import com.example.hms.entity.Role;
import com.example.hms.entity.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JwtServiceTest {
    private static final String SECRET = "MDEyMzQ1Njc4OWFiY2RlZjAxMjM0NTY3ODlhYmNkZWY=";

    @Test
    void generatesAndValidatesToken() {
        JwtService service = new JwtService(SECRET, 60_000);
        User user = user();
        String token = service.generateToken(user);
        assertEquals(user.getEmail(), service.extractUsername(token));
        assertTrue(service.isTokenValid(token, user.getEmail()));
        assertFalse(service.isTokenValid(token, "other@example.com"));
    }

    @Test
    void rejectsExpiredAndMalformedTokens() throws InterruptedException {
        JwtService service = new JwtService(SECRET, 1);
        String token = service.generateToken(user());
        Thread.sleep(10);
        assertTrue(service.isTokenExpired(token));
        assertFalse(service.isTokenValid("not-a-token", "navya@example.com"));
    }

    private User user() {
        User user = new User(); user.setEmail("navya@example.com"); user.setRole(Role.USER); return user;
    }
}
