package io.github.alprkeskin.search.service.extcall;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@FeignClient(name = "${feign-client.currency.name}", url = "${feign-client.currency.url}")
interface CurrencyFeignService {
    @GetMapping(value = "/currencies")
    ResponseEntity<Map<String, String>> getCurrencies();
}
