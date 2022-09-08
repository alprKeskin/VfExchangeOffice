package io.github.alprkeskin.common.model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MutatedCurrency implements Serializable {
    private String currencyCode;
    private double transactionAmountInUsdParity;
    private boolean isPump;
}
