package com.itpu.internship2.digital_cinema.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SpringConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Digital Cinema API")
                        .description("REST API for cinema seats reservation: manage places, movies, and sessions.")
                        .version("1.1.0")
                        .contact(new Contact()
                                .name("ITPU Student - Maksudbek Makhmudov")
                                .email("Maksudbek_Makhmudov@student.itpu.uz"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0")))
                .servers(List.of(
                        new Server().url("https://itpu-digital-cinema-2s7ce.ondigitalocean.app").description("Production server"),
                        new Server().url("http://localhost:8080").description("Local development server")
                ));
    }
}
