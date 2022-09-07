package io.github.alprkeskin.common.model.response;

import io.github.alprkeskin.common.model.role.AuthorityRole;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserAuthenticationResponseTest {
    @Test
    public void testConstructors() {
        UserAuthenticationResponse response1 = new UserAuthenticationResponse();
        UserAuthenticationResponse response2 = new UserAuthenticationResponse(false, false, null, null);

        assertNotNull(response1);
        assertNotNull(response2);
    }

    @Test
    public void testBuilder() {
        UserAuthenticationResponse response = UserAuthenticationResponse.builder()
                .authenticationFail(true).authorizationFail(true)
                .exception(new NullPointerException()).role(AuthorityRole.ADMIN).build();

        assertNotNull(response);
        assertTrue(response.isAuthenticationFail());
        assertTrue(response.isAuthorizationFail());
        assertNotNull(response.getException());
        Assertions.assertEquals(AuthorityRole.ADMIN, response.getRole());
    }

    @Test
    public void testMutators() {
        UserAuthenticationResponse response = new UserAuthenticationResponse();

        response.setAuthenticationFail(true);
        response.setAuthorizationFail(true);
        response.setException(new Throwable());
        response.setRole(AuthorityRole.ADMIN);

        assertTrue(response.isAuthenticationFail());
        assertTrue(response.isAuthorizationFail());
        assertNotNull(response.getException());
        Assertions.assertEquals(AuthorityRole.ADMIN, response.getRole());
    }
}
