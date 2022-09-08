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
    private int amount;
    private double value;

    public void incrementAmount(int value) {
        this.amount += value;
    }

    public void decrementAmount(int value) {
        this.amount -= value;
        if(this.amount < 0)
            this.amount = 0;
    }

    public void incrementValue(double valueAgainstUsd) {
        this.value += valueAgainstUsd;
    }

    public void decrementValue(double valueAgainstUsd) {
        this.value -= valueAgainstUsd;
        if(this.value < 0)
            this.value = 0;
    }

}
