package io.github.alprkeskin.auth;

import io.github.alprkeskin.common.aspects.EnableCommonAspects;
import io.github.alprkeskin.swagger.EnableSwagger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableSwagger
@EnableCommonAspects
@SpringBootApplication
public class MsAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsAuthApplication.class, args);
    }

}
