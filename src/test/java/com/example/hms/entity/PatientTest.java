package com.example.hms.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PatientTest {
    @Test
    void storesPatientFields() {
        Patient patient = new Patient();
        patient.setName("Rahul Kumar");
        patient.setAge(27);
        patient.setGender("Male");
        patient.setDisease("Fever");
        patient.setAddress("Chennai");
        patient.setPhoneNumber("9876543210");
        assertEquals("Rahul Kumar", patient.getName());
        assertEquals(27, patient.getAge());
        assertEquals("Male", patient.getGender());
        assertEquals("Fever", patient.getDisease());
        assertEquals("Chennai", patient.getAddress());
        assertEquals("9876543210", patient.getPhoneNumber());
    }
}
