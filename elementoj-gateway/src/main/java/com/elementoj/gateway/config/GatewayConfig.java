package com.elementoj.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("resource-server", r -> r
                        .path("/api/**")
                        .filters(f -> f
                                .tokenRelay() // Enable TokenRelay filter
                                .removeRequestHeader("Cookie") // Remove Cookie header
                        )
                        .uri("https://your-resource-server.com")
                )
                .build();
    }
}
