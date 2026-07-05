package com.itpu.internship2.digital_cinema.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.itpu.internship2.digital_cinema.repository")
@EntityScan(basePackages = "com.itpu.internship2.digital_cinema.entity")


public class DatabaseConfig {
}
