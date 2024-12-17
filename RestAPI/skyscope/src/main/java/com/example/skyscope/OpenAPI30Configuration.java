package com.example.skyscope;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.info.Contact;

@Configuration
public class OpenAPI30Configuration {
    @Bean
    public OpenAPI customizeOpenAPI() {
        final String securitySchemeName = "bearerAuth";  // The name of the security scheme

        return new OpenAPI()
            .info(new Info()
                .title("SkyScope")
                .version("1.0.0")
                .description("This project is used for finding trends in the recent world about a particular word!")
                .termsOfService("Copyright@2024")
                .contact(new Contact()
                    .name("Manjiri Chandure")
                    .email("chanduremanjiri@gmail.com")))
            .addSecurityItem(new SecurityRequirement()
                .addList(securitySchemeName))  // Link the security item to the scheme
            .components(new Components()
                .addSecuritySchemes(securitySchemeName, new SecurityScheme()
                    .name(securitySchemeName)
                    .type(SecurityScheme.Type.HTTP)
                    .scheme("bearer")
                    .bearerFormat("JWT")));  // Define JWT scheme for bearer authentication
    }
}
