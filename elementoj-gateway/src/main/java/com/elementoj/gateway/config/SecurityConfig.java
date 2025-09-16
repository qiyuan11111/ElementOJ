package com.elementoj.gateway.config;

import com.elementoj.gateway.repository.RedisOAuth2AuthorizedClientRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.client.InMemoryReactiveOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.web.server.AuthenticatedPrincipalServerOAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.server.ServerOAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.server.WebSessionServerOAuth2AuthorizedClientRepository;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {
    private final RedisOAuth2AuthorizedClientRepository authorizedClientRepository;

    public SecurityConfig(RedisOAuth2AuthorizedClientRepository authorizedClientRepository) {
        this.authorizedClientRepository = authorizedClientRepository;
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers("/auth/**").permitAll() // 公开路径无需认证
                        .pathMatchers("/.well-known/**", "/**.ico", "/error").permitAll()
                        .anyExchange().authenticated() // 其他所有请求需认证
                )
//                .oauth2Login(Customizer.withDefaults())
                .oauth2Login(oauth2 -> oauth2
                        .authorizedClientRepository(authorizedClientRepository)
                )
                .oauth2Client(oauth2 -> oauth2
                        .authorizedClientRepository(authorizedClientRepository)
                )
                // 不作为资源服务器：不要配置 oauth2ResourceServer(...)
                .csrf(ServerHttpSecurity.CsrfSpec::disable);

        return http.build();
    }


}

