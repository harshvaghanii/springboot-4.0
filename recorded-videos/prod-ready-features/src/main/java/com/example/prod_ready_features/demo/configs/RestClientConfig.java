package com.example.prod_ready_features.demo.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Value("${weather.api.url}")
    private String weatherApiUrl;

    @Value("${weather.api.key}")
    private String apiKey;

    @Value("${weather.api.host}")
    private String apiHost;

    @Bean
    public RestClient weatherApiRestClient() {
        return RestClient.builder()
                .baseUrl(weatherApiUrl)
                .defaultHeader("x-rapidapi-key", apiKey)
                .defaultHeader("x-rapidapi-host", apiHost)
                .build();
    }

}
