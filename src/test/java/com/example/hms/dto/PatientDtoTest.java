package com.example.hms.dto;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PatientDtoTest {
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void validatesPatientFields() {
        PatientDto dto = new PatientDto();
        assertFalse(validator.validate(dto).isEmpty());
        dto.setName("Rahul Kumar");
        dto.setAge(27);
        dto.setGender("Male");
        dto.setDisease("Fever");
        dto.setAddress("Chennai");
        dto.setPhoneNumber("9876543210");
        assertTrue(validator.validate(dto).isEmpty());
    }
}
