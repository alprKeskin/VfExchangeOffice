package io.alprkeskin.wallet.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

@Getter
@Setter
public class AssetTransaction {
    // email of the user that carries out transaction (e.g. deposit / withdraw 100 TRY)
    private String email;
    // asset code (currency code) that is being transacted (e.g. TRY)
    private String assetCode;
    // amount of asset to be transacted (e.g. 100 TRY)
    private int amount;

    @Override
    public String toString() {
        return String.format("[email: {}, assetCode: {}, amount: {}]", email, assetCode, amount);
    }
}
