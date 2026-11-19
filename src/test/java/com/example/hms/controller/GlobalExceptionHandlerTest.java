package com.example.hms.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GlobalExceptionHandlerTest {
    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    void mapsStatusExceptionsToErrorResponse() {
        var response = handler.handleStatus(new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found"));
        assertEquals(404, response.getBody().get("status"));
        assertEquals("Patient not found", response.getBody().get("error"));
    }

    @Test
    void hidesUnexpectedExceptionDetails() {
        var response = handler.handleUnexpected(new IllegalStateException("internal detail"));
        assertEquals(500, response.getStatusCode().value());
        assertEquals("An unexpected error occurred", response.getBody().get("error"));
    }
}
