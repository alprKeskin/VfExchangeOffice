package io.github.alprkeskin.auth.service;

import io.github.alprkeskin.auth.model.supplier.UserEntitySupplier;
import io.github.alprkeskin.auth.service.user.UserServiceMediator;
import io.github.alprkeskin.common.exception.AuthorizationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthDetailServiceTest {
    @InjectMocks
    private AuthDetailService service;
    @Mock
    private UserServiceMediator userServiceMediator;

    @Test
    public void testLoadUserByEmail() {
        assertThrows(AuthorizationException.class, () -> service.loadUserByEmail("email"));
        when(userServiceMediator.getUserByEmail("email")).thenReturn(UserEntitySupplier.getModel(7));
        assertNotNull(service.loadUserByEmail("email"));
    }
}
