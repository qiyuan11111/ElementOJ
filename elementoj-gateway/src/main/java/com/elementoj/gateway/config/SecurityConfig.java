package com.elementoj.gateway.config;

import com.elementoj.gateway.repository.RedisOAuth2AuthorizedClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@Configuration
@EnableWebFluxSecurity
@Slf4j
public class SecurityConfig {
    private final RedisOAuth2AuthorizedClientRepository authorizedClientRepository;

    public SecurityConfig(RedisOAuth2AuthorizedClientRepository authorizedClientRepository) {
        this.authorizedClientRepository = authorizedClientRepository;
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers("/auth/**").permitAll()
                        .pathMatchers("/.well-known/**", "/**.ico", "/error").permitAll()
                        .anyExchange().authenticated()
                )
                .oauth2Login(oauth2 -> oauth2
                        .authorizedClientRepository(authorizedClientRepository)
                        .authenticationFailureHandler((webFilterExchange, exception) -> {
                            ServerWebExchange exchange = webFilterExchange.getExchange();
                            log.error("OAuth2 login failed: {}", exception.getMessage(), exception);
                            return exchange.getResponse().writeWith(Mono.fromSupplier(() -> {
                                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                                exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
                                DataBufferFactory bufferFactory = exchange.getResponse().bufferFactory();
                                String body = "{\"error\": \"OAuth2 login failed\", \"message\": \"" +
                                        exception.getMessage().replace("\"", "\\\"") + "\"}";
                                return bufferFactory.wrap(body.getBytes(StandardCharsets.UTF_8));
                            }));
                        })
                )
                .oauth2Client(oauth2 -> oauth2.authorizedClientRepository(authorizedClientRepository))
                .csrf(ServerHttpSecurity.CsrfSpec::disable);

        return http.build();
    }
}