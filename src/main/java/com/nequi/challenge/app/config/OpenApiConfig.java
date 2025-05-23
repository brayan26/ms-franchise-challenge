package com.nequi.challenge.app.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
   @Bean
   public OpenAPI franquiciaAPI() {
      return new OpenAPI()
            .info(new Info()
                  .title("API de Franquicias - Challenge Nequi")
                  .version("1.0.0")
                  .description("Gestión de franquicias, sucursales y productos"));
   }
}

