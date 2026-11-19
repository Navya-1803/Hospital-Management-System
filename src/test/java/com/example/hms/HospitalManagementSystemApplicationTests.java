package com.example.hms;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class HospitalManagementSystemApplicationTests {
    @Test
    void applicationClassIsConfiguredForSpringBoot() {
        assertNotNull(HospitalManagementSystemApplication.class.getAnnotation(SpringBootApplication.class));
    }
}
