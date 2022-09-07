package io.github.alprkeskin.auth.service.user;

import io.github.alprkeskin.auth.service.user.UserCommandService;
import io.github.alprkeskin.auth.service.user.UserQueryService;
import io.github.alprkeskin.auth.service.user.UserServiceMediator;
import io.github.alprkeskin.auth.model.supplier.UserEntitySupplier;
import io.github.alprkeskin.auth.model.table.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceMediatorTest {
    @InjectMocks
    private UserServiceMediator service;
    @Mock
    private UserQueryService queryService;
    @Mock
    private UserCommandService commandService;

    @Test
    public void testGetUserByEmail() {
        when(queryService.getUserByEmail(anyString())).thenReturn(UserEntitySupplier.getModel(2));
        assertNotNull(service.getUserByEmail("email"));
    }

    @Test
    public void testPersistUser() {
        UserEntity entity = UserEntitySupplier.getModel(2);
        when(commandService.persist(any(UserEntity.class))).thenReturn(entity);
        assertNotNull(service.persistUser(entity));
    }

    @Test
    public void testDeleteUser() {
        assertDoesNotThrow(() -> service.deleteUser("email"));
    }
}
