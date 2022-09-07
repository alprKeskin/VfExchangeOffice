package io.github.alprkeskin.auth.service.user;

import io.github.alprkeskin.auth.model.table.UserEntity;
import io.github.alprkeskin.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class UserQueryService {
    @Autowired
    private UserRepository userRepository;

    UserEntity getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }
}
