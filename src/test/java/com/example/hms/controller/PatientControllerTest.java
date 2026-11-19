package com.example.hms.controller;

import com.example.hms.dto.PatientDto;
import com.example.hms.service.PatientService;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class PatientControllerTest {
    private PatientService service;
    private MockMvc mvc;

    @BeforeEach
    void setUp() {
        service = mock(PatientService.class);
        mvc = MockMvcBuilders.standaloneSetup(new PatientController(service))
                .setControllerAdvice(new GlobalExceptionHandler()).build();
    }

    @Test
    void addsPatient() throws Exception {
        when(service.createPatient(any(PatientDto.class))).thenReturn(patient());
        mvc.perform(post("/api/patients").contentType(MediaType.APPLICATION_JSON).content(patientJson()))
                .andExpect(status().isCreated()).andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void getsAllPatients() throws Exception {
        when(service.getAllPatients()).thenReturn(List.of(patient()));
        mvc.perform(get("/api/patients")).andExpect(status().isOk()).andExpect(jsonPath("$[0].name").value("Rahul"));
    }

    @Test
    void getsPatientById() throws Exception {
        when(service.getPatientById(1L)).thenReturn(patient());
        mvc.perform(get("/api/patients/1")).andExpect(status().isOk()).andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void updatesPatient() throws Exception {
        when(service.updatePatient(org.mockito.ArgumentMatchers.eq(1L), any(PatientDto.class))).thenReturn(patient());
        mvc.perform(put("/api/patients/1").contentType(MediaType.APPLICATION_JSON).content(patientJson()))
                .andExpect(status().isOk()).andExpect(jsonPath("$.name").value("Rahul"));
    }

    @Test
    void deletesPatient() throws Exception {
        mvc.perform(delete("/api/patients/1")).andExpect(status().isOk()).andExpect(content().string("Patient deleted successfully"));
        verify(service).deletePatient(1L);
    }

    private PatientDto patient() {
        PatientDto dto = new PatientDto(); dto.setId(1L); dto.setName("Rahul"); dto.setAge(27);
        dto.setGender("Male"); dto.setDisease("Fever"); dto.setAddress("Chennai"); dto.setPhoneNumber("9876543210"); return dto;
    }

    private String patientJson() {
        return "{\"name\":\"Rahul\",\"age\":27,\"gender\":\"Male\",\"disease\":\"Fever\",\"address\":\"Chennai\",\"phoneNumber\":\"9876543210\"}";
    }
}
