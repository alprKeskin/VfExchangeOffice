package io.github.alprkeskin.common.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MutatedCurrency {
    private String currencyCode;
    private double transactionAmountInUsdParity;
    private boolean isPump;
}
