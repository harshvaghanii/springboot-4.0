package com.example.spring_security_fundamentals.spring_security_fundamentals.configs;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }

}
