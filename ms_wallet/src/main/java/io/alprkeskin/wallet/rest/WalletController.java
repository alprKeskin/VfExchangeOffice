package io.alprkeskin.wallet.rest;

import io.alprkeskin.wallet.model.Asset;
import io.alprkeskin.wallet.model.AssetTransaction;
import io.alprkeskin.wallet.model.document.Assets;
import io.alprkeskin.wallet.service.AssetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/wallet")
public class WalletController {
    @Autowired
    AssetService assetService;

    @GetMapping("assets")
    @CrossOrigin
    public ResponseEntity<Map<String, Asset>> getUserAssets(@RequestHeader(name = "email") String email) {
        log.info("WalletController::getUserAssets(String email = {})", email);
        return ResponseEntity.ok(assetService.getUserAssets(email));
    }

    @PostMapping("deposit")
    @CrossOrigin
    public ResponseEntity<Map<String, Asset>> depositMoney(@RequestBody AssetTransaction assetTransaction) {
        log.info("WalletController::depositMoney(AssetTransaction assetTransaction = {})", assetTransaction);
        return ResponseEntity.ok(assetService.deposit(assetTransaction));
    }

    @PostMapping("withdraw")
    @CrossOrigin
    public ResponseEntity<Map<String, Asset>> withdrawMoney(@RequestBody AssetTransaction assetTransaction) {
        log.info("WalletController::withdrawMoney(AssetTransaction assetTransaction = {})", assetTransaction);
        return ResponseEntity.ok(assetService.withdraw(assetTransaction));
    }
}
