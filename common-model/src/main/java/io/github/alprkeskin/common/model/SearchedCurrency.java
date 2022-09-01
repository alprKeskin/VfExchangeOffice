package io.github.alprkeskin.common.model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchedCurrency implements Serializable {
    private String date;
    private String currencyCode;
}
