package com.example.hms.controller;

import com.example.hms.dto.AuthRequest;
import com.example.hms.dto.AuthResponse;
import com.example.hms.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class AuthControllerTest {
    private AuthService service;
    private MockMvc mvc;

    @BeforeEach
    void setUp() {
        service = mock(AuthService.class);
        mvc = MockMvcBuilders.standaloneSetup(new AuthController(service))
                .setControllerAdvice(new GlobalExceptionHandler()).build();
    }

    @Test
    void registersUser() throws Exception {
        when(service.registerUser(any(AuthRequest.class))).thenReturn(new AuthResponse(null, "User registered successfully"));
        mvc.perform(post("/api/auth/register").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Navya\",\"email\":\"navya@example.com\",\"password\":\"secure-pass\"}"))
                .andExpect(status().isCreated()).andExpect(jsonPath("$.message").value("User registered successfully"));
    }

    @Test
    void logsInUser() throws Exception {
        when(service.loginUser(any(AuthRequest.class))).thenReturn(new AuthResponse("jwt-token", "Login successful"));
        mvc.perform(post("/api/auth/login").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"navya@example.com\",\"password\":\"secure-pass\"}"))
                .andExpect(status().isOk()).andExpect(jsonPath("$.token").value("jwt-token"));
    }

    @Test
    void rejectsInvalidLoginRequest() throws Exception {
        mvc.perform(post("/api/auth/login").contentType(MediaType.APPLICATION_JSON).content("{}"))
                .andExpect(status().isBadRequest()).andExpect(jsonPath("$.error").value("Validation failed"));
    }
}
