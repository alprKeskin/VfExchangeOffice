package io.github.alprkeskin.auth.model.request;

import io.github.alprkeskin.common.model.role.AuthorityRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
    private String email;
    private String username;
    private String password;
    private AuthorityRole role;
}
