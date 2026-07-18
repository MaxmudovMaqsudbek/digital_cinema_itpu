package com.itpu.internship2.digital_cinema.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Digital Cinema API",
                version = "1.1.0",
                description = "REST API for cinema seats reservation: manage places, movies, and sessions.",
                contact = @Contact(
                        name = "ITPU Student - Maksudbek Makhmudov"
                ),
                license = @License(
                        name = "Apache 2.0"
                )
        ),
        servers = {
                @Server(url = "https://itpu-digital-cinema-2s7ce.ondigitalocean.app", description = "Production server"),
                @Server(url = "http://localhost:8080", description = "Local development server")
        }
)
public class OpenApiConfig {
}
