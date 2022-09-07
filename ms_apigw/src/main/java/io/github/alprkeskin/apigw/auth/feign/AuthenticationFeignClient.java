package io.github.alprkeskin.apigw.auth.feign;

import io.github.alprkeskin.common.model.response.UserAuthenticationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(value = "${feign-client.authentication.name}", url = "${feign-client.authentication.url}")
interface AuthenticationFeignClient {
    @GetMapping
    ResponseEntity<UserAuthenticationResponse> checkAuth(@RequestHeader("token") String token);
}
