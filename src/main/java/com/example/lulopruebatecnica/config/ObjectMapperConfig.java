package com.example.lulopruebatecnica.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectMapperConfig {

    @Bean
    public ModelMapper objectMapper() {
        return new ModelMapper();
    }
}
