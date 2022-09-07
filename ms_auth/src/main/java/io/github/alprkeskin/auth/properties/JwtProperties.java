package io.github.alprkeskin.auth.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    private String secretKey = "rzxlszyykpbgqcflzxsqcysyhljt";
    private long validityInMs = 3600000;
}
