package io.alprkeskin.wallet.model;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyRates {
    @Id
    private String id;
    private int timestamp;
    private String base;
    private Map<String, Double> rates;
}
