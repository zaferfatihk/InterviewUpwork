package org.yourcompany.yourproject;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
// @ComponentScan(basePackages = "org.yourcompany.yourproject")
@EnableJpaRepositories(basePackages = "org.yourcompany.yourproject.dao")
@EntityScan(basePackages = "org.yourcompany.yourproject.model")
public class TestConfig {
}