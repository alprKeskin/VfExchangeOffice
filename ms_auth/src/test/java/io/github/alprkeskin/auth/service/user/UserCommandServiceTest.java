package io.github.alprkeskin.auth.service.user;

import io.github.alprkeskin.auth.service.user.UserCommandService;
import io.github.alprkeskin.auth.model.supplier.UserEntitySupplier;
import io.github.alprkeskin.auth.model.table.UserEntity;
import io.github.alprkeskin.auth.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserCommandServiceTest {
    @InjectMocks
    private UserCommandService service;

    @Mock
    private UserRepository repository;

    @Test
    public void testPersist() {
        UserEntity entity = UserEntitySupplier.getModel(7);

        when(repository.save(entity)).thenReturn(entity);

        assertEquals(entity, service.persist(entity));
    }

    @Test
    public void testDelete() {
        assertDoesNotThrow(() -> service.delete("email"));
    }
}
