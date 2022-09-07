package io.alprkeskin.wallet.rest;

import io.alprkeskin.wallet.model.Asset;
import io.alprkeskin.wallet.model.AssetTransaction;
import io.alprkeskin.wallet.service.AssetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/wallet")
public class WalletController {
    AssetService assetService;

    @GetMapping("deposit/{currency_code}/{email}")
    @CrossOrigin
    public ResponseEntity<Map<String, Asset>> depositMoney(@PathVariable(value = "currency_code") String currencyCode,
                                                           @PathVariable(value = "email") String email,
                                                           @RequestParam(name = "amount") int amount) {
        log.info("WalletController::depositMoney(String currencyCode = {}, String email = {}, int amount = {})", currencyCode, email, amount);
        AssetTransaction assetTransaction = AssetTransaction.builder().
                assetCode(currencyCode).
                email(email).
                amount(amount).
                build();

        return ResponseEntity.ok(assetService.deposit(assetTransaction));
    }

    @GetMapping()
    @CrossOrigin
    public ResponseEntity<Map<String, Asset>> withdrawMoney(@PathVariable(value = "currency_code") String currencyCode,
                              @PathVariable(value = "email") String email,
                              @RequestParam(name = "amount") int amount) {
        log.info("WalletController::withdrawMoney(String currencyCode = {}, String email = {}, int amount = {})\", currencyCode, email, amount)");
        AssetTransaction assetTransaction = AssetTransaction.builder().
                assetCode(currencyCode).
                email(email).
                amount(amount).
                build();

        return ResponseEntity.ok(assetService.withdraw(assetTransaction));
    }
}
