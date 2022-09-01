package io.github.alprkeskin.currency;

import io.github.berkayelken.bananazura.aop.annotation.EnableBananazuraLoggingHandling;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
@EnableBananazuraLoggingHandling
@SpringBootApplication
@EnableScheduling
public class MsCurrencyApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsCurrencyApplication.class, args);
    }

}
