package com.example.crud_pessoa.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {
    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI().info(
                new Info().title("People CRUD API")
                        .version("1.0.0")
                        .description("API documentation for managing people and their addresses")
        );
    }

    @Bean
    public GroupedOpenApi publicAPI(){
        return GroupedOpenApi.builder()
                .group("person")
                .pathsToMatch("/**")
                .build();
    }

}
