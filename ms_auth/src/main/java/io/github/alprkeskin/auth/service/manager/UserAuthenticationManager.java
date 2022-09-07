package io.github.alprkeskin.auth.service.manager;

import io.github.alprkeskin.auth.service.AuthDetailService;
import io.github.alprkeskin.common.exception.AuthorizationException;
import io.github.alprkeskin.common.exception.InvalidAuthenticationTokenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserAuthenticationManager implements AuthenticationManager {
    @Autowired
    private AuthDetailService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getPrincipal() + "";
        String password = authentication.getCredentials() + "";

        UserDetails user = getUser(email);

        PasswordEncoder encoder = new BCryptPasswordEncoder();

        if (encoder.matches(password, user.getPassword()))
        {
            throw new InvalidAuthenticationTokenException("Bad credentials", HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);
        }

        if (!user.isEnabled())
        {
            throw new DisabledException("User account is not active");
        }

        return new UsernamePasswordAuthenticationToken(email, user.getPassword(), user.getAuthorities());
    }

    public void unauthenticated(String email) {
        UsernamePasswordAuthenticationToken.unauthenticated(email, null);
    }

    private UserDetails getUser(String email) {
        UserDetails user = userDetailsService.loadUserByEmail(email);
        if(user == null)
            throw new AuthorizationException("User cannot be found. Authorization is failed.");

        return user;
    }
}
