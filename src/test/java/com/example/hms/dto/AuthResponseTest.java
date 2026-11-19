package com.example.hms.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AuthResponseTest {
    @Test
    void carriesTokenAndMessage() {
        AuthResponse response = new AuthResponse("jwt", "Login successful");
        assertEquals("jwt", response.getToken());
        assertEquals("Login successful", response.getMessage());
    }
}
