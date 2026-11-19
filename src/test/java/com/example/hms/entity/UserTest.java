package com.example.hms.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class UserTest {
    @Test
    void storesUserFieldsAndDefaultsToUserRole() {
        User user = new User();
        user.setName("Navya");
        user.setEmail("navya@example.com");
        user.setPassword("encoded");
        assertNull(user.getId());
        assertEquals("Navya", user.getName());
        assertEquals("navya@example.com", user.getEmail());
        assertEquals("encoded", user.getPassword());
        assertEquals(Role.USER, user.getRole());
    }
}
