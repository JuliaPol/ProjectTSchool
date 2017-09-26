package com.tsystems.ecare.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = {
        "com.tsystems.ecare.dao"
})
@EnableJpaRepositories
public class AppConfig {
}
