package com.example.skyscope;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;

@Configuration
public class OpenAPI30Configuration {
    @Bean
    public OpenAPI customizeOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("SkyScope")
                .version("1.0.0")
                .description("This Project used for finding trend in resent world about particular word!")
                .termsOfService("Copyright@2024")
                .contact(new Contact()
                    .name("Manjiri Chandure")
                    .email("chanduremanjiri@gmail.com"))

            );
        
        // Later, you can uncomment and add JWT security configuration:
        // final String securitySchemeName = "bearerAuth";
        // return new OpenAPI()
        //         .addSecurityItem(new SecurityRequirement()
        //                 .addList(securitySchemeName))
        //         .components(new Components()
        //                 .addSecuritySchemes(securitySchemeName, new SecurityScheme()
        //                         .name(securitySchemeName)
        //                         .type(SecurityScheme.Type.HTTP)
        //                         .scheme("bearer")
        //                         .bearerFormat("JWT")));
    }
}
