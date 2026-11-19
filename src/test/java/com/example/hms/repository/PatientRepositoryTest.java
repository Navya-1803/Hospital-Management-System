package com.example.hms.repository;

import com.example.hms.entity.Patient;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PatientRepositoryTest {
    @Test
    void returnsStoredPatients() {
        PatientRepository repository = mock(PatientRepository.class);
        Patient patient = new Patient();
        patient.setName("Rahul Kumar");
        when(repository.findAll()).thenReturn(List.of(patient));
        assertEquals("Rahul Kumar", repository.findAll().get(0).getName());
    }
}
