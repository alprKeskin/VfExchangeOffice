package io.github.alprkeskin.currency.service.endpoints;

import lombok.AllArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
public enum Endpoint {
    LATEST("latest.json"), HISTORICAL("historical/"), CURRENCIES("currencies.json");

    private final String operationType;

    public String getOperationType(LocalDate... date) {
        if (this.equals(HISTORICAL)) {
            if (date.length == 0) {
                throw new RuntimeException("Date is not given for historical endpoint!");
            }
            return this.operationType + date[0] + ".json";
        }
        if (date.length != 0) throw new RuntimeException("Date must not be given for latest and currencies endpoints!");

        return this.operationType;
    }
}
