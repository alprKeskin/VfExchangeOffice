package io.alprkeskin.wallet.model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Asset implements Serializable {
    // value currency'nin miktarı
    private int value;
    private int valueAgainstUsd;

    public void incrementValue(int value) {
        this.value += value;
    }

    public void decrementValue(int value) {
        this.value -= value;
        if(this.value < 0)
            this.value = 0;
    }

    public void incrementUsdValue(double valueAgainstUsd) {
        this.valueAgainstUsd += valueAgainstUsd;
    }

    public void decrementUsdValue(double valueAgainstUsd) {
        this.valueAgainstUsd -= valueAgainstUsd;
        if(this.valueAgainstUsd < 0)
            this.valueAgainstUsd = 0;
    }

}
