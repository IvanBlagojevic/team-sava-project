package com.assignment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.assignment.apigateway.controllers")).build().apiInfo(metaData());
    }

    private ApiInfo metaData() {
        return new ApiInfo(
                "User service",
                "User service for Team Sava project",
                "0.0.1-SNAPSHOT",
                null,
                null,
                null,
                null, new ArrayList<>());
    }
}
