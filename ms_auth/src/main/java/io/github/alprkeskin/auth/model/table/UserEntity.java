package io.github.alprkeskin.auth.model.table;

import io.github.alprkeskin.common.model.role.AuthorityRole;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "USERS")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;
    @Column(name = "EMAIL", unique = true, nullable = false)
    private String email;
    @Column(name = "USERNAME", nullable = false)
    private String username;
    @Column(name = "PASSWORD", nullable = false)
    private String password;
    @Column(name = "ROLE")
    private int role;

    public GrantedAuthority getAuthority() {
        return AuthorityRole.getRole(getRole()).getAuthority();
    }
}
