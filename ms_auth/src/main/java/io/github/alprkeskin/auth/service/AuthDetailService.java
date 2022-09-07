package io.github.alprkeskin.auth.service;

import io.github.alprkeskin.auth.model.UserAuthModel;
import io.github.alprkeskin.auth.model.table.UserEntity;
import io.github.alprkeskin.auth.service.user.UserServiceMediator;
import io.github.alprkeskin.common.exception.AuthorizationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthDetailService implements UserDetailsService {
    @Autowired
    private UserServiceMediator userServiceMediator;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    public UserDetails loadUserByEmail(String email) {
        try {
            UserEntity entity = userServiceMediator.getUserByEmail(email);
            return new UserAuthModel(entity);
        } catch (Throwable t) {
            throw new AuthorizationException("User not found.");
        }
    }
}
