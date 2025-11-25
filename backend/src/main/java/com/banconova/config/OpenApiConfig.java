
package com.banconova.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI bancoNovaOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Banco Nova API")
                        .description("API REST para la plataforma bancaria Banco Nova")
                        .version("v1.0"))
                .externalDocs(new ExternalDocumentation()
                        .description("Banco Nova GitHub")
                        .url("https://github.com/madandev02/banco-nova"));
    }
}
