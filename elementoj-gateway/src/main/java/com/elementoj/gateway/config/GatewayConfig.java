package com.elementoj.gateway.config;


import com.elementoj.gateway.filter.RedisTokenRelayGatewayFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    private final RedisTokenRelayGatewayFilter redisTokenRelayFilter;

    public GatewayConfig(RedisTokenRelayGatewayFilter redisTokenRelayFilter) {
        this.redisTokenRelayFilter = redisTokenRelayFilter;
    }

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("elementoj-auth", r -> r
                        .path("/auth/**")
                        .filters(f -> f
                                .stripPrefix(1)
                                .filter(redisTokenRelayFilter)
                                .tokenRelay() // Enable TokenRelay filter
                        )
                        .uri("lb://elementoj-auth:8301")
                )
                .route("elementoj-module-news", r -> r
                        .path("/news/**")
                        .filters(f -> f
//                                .stripPrefix(1)
                                .tokenRelay()
                        )
                        .uri("lb://elementoj-module-news:8401")
                )
                .build();
    }


}
