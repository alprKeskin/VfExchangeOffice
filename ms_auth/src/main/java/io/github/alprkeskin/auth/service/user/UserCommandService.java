package io.github.alprkeskin.auth.service.user;

import io.github.alprkeskin.auth.model.table.UserEntity;
import io.github.alprkeskin.auth.repository.UserRepository;
import io.github.alprkeskin.auth.util.CryptoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class UserCommandService {
    @Autowired
    private UserRepository userRepository;

    UserEntity persist(UserEntity entity) {
        entity.setPassword(CryptoUtils.encodeWithSHA256(entity.getEmail(), entity.getPassword()));

        return userRepository.save(entity);
    }

    void delete(String email) {
        userRepository.deleteByEmail(email);
    }
}
