package io.github.alprkeskin.swagger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.ServletContext;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@EnableWebMvc
@Configuration
@ComponentScan({"io.github.alprkeskin.swagger"})
class SwaggerConfig {
    @Autowired
    private ServletContext servletContext;

    private static final List<String> excludedPaths =
            Stream.of("/management","/swagger","/error", "/actuator").collect(toList());

    @Bean
    public Docket api() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
                .paths(this::isValidUrl).build();

        ApiInfoBuilder builder = new ApiInfoBuilder();
        builder.title("Fleet Application Documentation");
        docket.apiInfo(builder.build());

        return docket;
    }

    private boolean isValidUrl(String url) {
        return !excludedPaths.stream().map(servletContext.getContextPath()::concat).anyMatch(url::startsWith);
    }
}
