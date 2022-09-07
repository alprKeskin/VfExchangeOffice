package io.github.alprkeskin.apigw.properties;

import io.github.alprkeskin.apigw.util.EndpointUtils;
import io.github.alprkeskin.common.model.role.UserType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpMethod;

@Getter
@Setter
public class EndpointProperties {
    private UserType validUserType;
    private HttpMethod method;
    private String endpoint;

    public String getRealEndpoint() {
        return EndpointUtils.getEndpoint(method.name(), endpoint);
    }
}
