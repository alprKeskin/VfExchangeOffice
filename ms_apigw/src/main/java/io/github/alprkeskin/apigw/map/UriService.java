package io.github.alprkeskin.apigw.map;

import io.github.alprkeskin.apigw.util.EndpointUtils;
import io.github.alprkeskin.apigw.properties.EndpointProperties;
import io.github.alprkeskin.apigw.properties.GatewayProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

@Service
public class UriService {
    @Autowired
    private GatewayProperties gatewayProperties;

    private Map<String, EndpointProperties> endpointMap;

    private List<String> authenticateEndpoints;

    private List<String> authenticateEndpointsWithWildCard;

    @PostConstruct
    private void afterInit() {
        endpointMap = Arrays.stream(gatewayProperties.getEndpoints()).filter(Objects::nonNull)
                .collect(toMap(EndpointProperties::getRealEndpoint, endpoint -> endpoint));
        authenticateEndpoints = getAuthenticateEndpoints();
        authenticateEndpointsWithWildCard = getAuthenticateEndpointsWithWildCard();
    }

    public EndpointProperties getEndpoint(String endpointPath) {
        return endpointMap.get(endpointPath);
    }

    public boolean containsEndpoint(String endpointPath) {
        return endpointMap.containsKey(endpointPath);
    }

    public String getAuthenticatedEndpoint(HttpMethod httpMethod, String endpoint) {
        String realEndpoint = EndpointUtils.getEndpoint(httpMethod.name(), endpoint);

        Optional<String> normalEndpointOptional = authenticateEndpoints.stream().filter(realEndpoint::equalsIgnoreCase).findFirst();
        if(normalEndpointOptional.isPresent())
            return normalEndpointOptional.get();

        Optional<String> wildCardEndpointOptional = authenticateEndpointsWithWildCard.stream().filter(realEndpoint::startsWith).findFirst();
        if(wildCardEndpointOptional.isPresent())
            return wildCardEndpointOptional.get() + "**";
        return null;
    }

    private List<String> getAuthenticateEndpoints() {
        return Arrays.stream(gatewayProperties.getEndpoints()) //
                .filter(Objects::nonNull) //
                .map(EndpointProperties::getRealEndpoint) //
                .filter(endpoint -> !endpoint.endsWith("**")) //
                .collect(toList());
    }

    private List<String> getAuthenticateEndpointsWithWildCard() {
        return Arrays.stream(gatewayProperties.getEndpoints()) //
                .filter(Objects::nonNull) //
                .map(EndpointProperties::getRealEndpoint) //
                .filter(endpoint -> endpoint.endsWith("**")) //
                .map(endpoint -> endpoint.substring(0, endpoint.length() - 2))
                .collect(toList());
    }
}
