package com.example.hms.dto;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AuthRequestTest {
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void requiresValidEmailAndPassword() {
        AuthRequest request = new AuthRequest();
        assertFalse(validator.validate(request).isEmpty());
        request.setEmail("navya@example.com");
        request.setPassword("secure-pass");
        assertTrue(validator.validate(request).isEmpty());
    }
}
