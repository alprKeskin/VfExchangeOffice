package io.github.alprkeskin.auth.model.response;

import io.github.alprkeskin.common.model.response.result.ResultEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthResponse {
    private ResultEntity result;
    private String email;
    private String token;
    private String name;
}
