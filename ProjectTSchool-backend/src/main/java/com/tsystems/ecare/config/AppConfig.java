package com.tsystems.ecare.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = {
        "com.tsystems.ecare.dao",
        "com.tsystems.ecare.service",
        "com.tsystems.ecare.validator",
        "com.tsystems.ecare.facade",
        "com.tsystems.ecare.util"
})
@EnableJpaRepositories
public class AppConfig {

}
