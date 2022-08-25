package io.github.alprkeskin.currency.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class CurrencyRates {
    @Id
    private String id;
    private int timestamp;
    private String base;
    private Map<String, Double> rates;
}
