package io.github.alprkeskin.apigw.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component("apiGatewayProperties")
@ConfigurationProperties(prefix = "authenticate-endpoints")
public class GatewayProperties {
    private EndpointProperties[] endpoints;
}
