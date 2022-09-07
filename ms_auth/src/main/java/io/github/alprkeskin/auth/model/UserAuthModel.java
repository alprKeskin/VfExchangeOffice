package io.github.alprkeskin.auth.model;

import io.github.alprkeskin.auth.model.table.UserEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Getter
@Setter
public class UserAuthModel extends User {
    private String email;
    private String password;
    private List<GrantedAuthority> grantedAuthoritiesList = new ArrayList<>();

    public UserAuthModel(UserEntity userEntity) {
        super(userEntity.getEmail(), userEntity.getPassword(), Stream.of(userEntity.getAuthority()).collect(toList()));
        setEmail(userEntity.getEmail());
        setPassword(userEntity.getPassword());
    }
}
