package io.github.alprkeskin.currency.config;

import io.github.alprkeskin.currency.service.CurrencyRatesMediatorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;

@Configuration
@EnableScheduling
@ConditionalOnProperty(name = "scheduling.enabled", matchIfMissing = true)
public class SchedulingConfiguration {
    @Autowired
    private CurrencyRatesMediatorService currencyRatesMediatorService;

    @Scheduled(cron = "*/10 * * * * *")
    public void getDailyRates() {
        currencyRatesMediatorService.getCurrencyRates(LocalDate.now());
    }
}
