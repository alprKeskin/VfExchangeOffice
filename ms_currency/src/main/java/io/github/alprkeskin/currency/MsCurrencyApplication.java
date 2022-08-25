package io.github.alprkeskin.currency;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MsCurrencyApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsCurrencyApplication.class, args);
    }

}
