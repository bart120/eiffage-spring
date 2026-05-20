package com.formation.hello_spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI apiManagementOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Formation Spring Boot API")
                        .description("API de gestion des formations")
                        .version("1.0.0"));
    }
}
