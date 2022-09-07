package io.github.alprkeskin.auth.service.user;

import io.github.alprkeskin.auth.service.user.UserQueryService;
import io.github.alprkeskin.auth.model.supplier.UserEntitySupplier;
import io.github.alprkeskin.auth.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserQueryServiceTest {
    @InjectMocks
    private UserQueryService service;

    @Mock
    private UserRepository repository;

    @Test
    public void testGetUserByEmail() {
        when(repository.findByEmail("email1")).thenReturn(Optional.of(UserEntitySupplier.getModel(3)));
        when(repository.findByEmail("email2")).thenReturn(Optional.empty());

        assertNotNull(service.getUserByEmail("email1"));
        assertNull(service.getUserByEmail("email2"));
    }
}
