package com.example.hms.service;

import com.example.hms.dto.PatientDto;
import com.example.hms.entity.Patient;
import com.example.hms.repository.PatientRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PatientServiceTest {
    private final PatientRepository repository = mock(PatientRepository.class);
    private final PatientService service = new PatientService(repository);

    @Test
    void createsPatient() {
        when(repository.save(any(Patient.class))).thenAnswer(call -> { Patient p = call.getArgument(0); p.setId(1L); return p; });
        assertEquals(1L, service.createPatient(dto()).getId());
    }

    @Test
    void listsPatients() {
        Patient patient = patient(); when(repository.findAll()).thenReturn(List.of(patient));
        assertEquals("Rahul", service.getAllPatients().get(0).getName());
    }

    @Test
    void fetchesPatientById() {
        when(repository.findById(1L)).thenReturn(Optional.of(patient()));
        assertEquals(1L, service.getPatientById(1L).getId());
    }

    @Test
    void updatesPatient() {
        when(repository.findById(1L)).thenReturn(Optional.of(patient()));
        when(repository.save(any(Patient.class))).thenAnswer(call -> call.getArgument(0));
        assertEquals("Updated", service.updatePatient(1L, dto()).getName());
    }

    @Test
    void deletesPatient() {
        Patient patient = patient(); when(repository.findById(1L)).thenReturn(Optional.of(patient));
        service.deletePatient(1L); verify(repository).delete(patient);
    }

    @Test
    void reportsMissingPatient() {
        when(repository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(ResponseStatusException.class, () -> service.getPatientById(2L));
    }

    private Patient patient() { Patient p = new Patient(); p.setId(1L); p.setName("Rahul"); p.setAge(27); p.setGender("Male"); p.setDisease("Fever"); p.setAddress("Chennai"); p.setPhoneNumber("9876543210"); return p; }
    private PatientDto dto() { PatientDto d = new PatientDto(); d.setName("Updated"); d.setAge(28); d.setGender("Male"); d.setDisease("Flu"); d.setAddress("Chennai"); d.setPhoneNumber("9876543210"); return d; }
}
