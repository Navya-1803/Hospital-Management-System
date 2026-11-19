package com.example.hms.repository;

import com.example.hms.entity.User;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserRepositoryTest {
    @Test
    void findsUserByEmail() {
        UserRepository repository = mock(UserRepository.class);
        User user = new User();
        user.setEmail("navya@example.com");
        when(repository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
        assertEquals(user, repository.findByEmail("navya@example.com").orElseThrow());
    }

    @Test
    void checksWhetherEmailExists() {
        UserRepository repository = mock(UserRepository.class);
        when(repository.existsByEmail("navya@example.com")).thenReturn(true);
        assertTrue(repository.existsByEmail("navya@example.com"));
    }
}
