package io.github.alprkeskin.apigw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MsApigwApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsApigwApplication.class, args);
    }

}
