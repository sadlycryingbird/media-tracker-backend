package com.uk.weird.media.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class DramaWebClientConfig {

    @Value("${tmdb.api.token}")
    private String apiToken;

    @Bean
    public WebClient tmdbClient() {
        return WebClient.builder()
                .baseUrl("https://api.themoviedb.org/3")
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + apiToken)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, "application/json;charset=utf-8")
                .build();
    }
}
