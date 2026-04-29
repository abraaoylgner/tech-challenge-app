package com.pos.tech.challenge.app.infra.springdoc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")))
                .addSecurityItem(new SecurityRequirement().addList("bearer-key"))
                .info(new Info()
                        .title("Tech Challenge API - Sistema de Restaurantes")
                        .description("API RESTful para gestão de usuários (Restaurantes e Clientes) desenvolvida para o Tech Challenge da Pós Tech-FIAP")
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("Abraao/Grupo 100")
                                .email("abraaoayrs@gmail.com")));
    }
}