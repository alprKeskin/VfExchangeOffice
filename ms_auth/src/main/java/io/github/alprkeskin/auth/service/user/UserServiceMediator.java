package io.github.alprkeskin.auth.service.user;

import io.github.alprkeskin.auth.model.table.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceMediator {
    @Autowired
    private UserCommandService commandService;
    @Autowired
    private UserQueryService queryService;

    public UserEntity getUserByEmail(String email) {
        return queryService.getUserByEmail(email);
    }

    public UserEntity persistUser(UserEntity entity) {
        return commandService.persist(entity);
    }

    public void deleteUser(String email) {
        commandService.delete(email);
    }

}
