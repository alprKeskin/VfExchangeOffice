package io.github.alprkeskin.common.model.response;

import io.github.alprkeskin.common.model.role.AuthorityRole;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAuthenticationResponse {
    private boolean authenticationFail;
    private boolean authorizationFail;
    private Throwable exception;
    private AuthorityRole role;
}
