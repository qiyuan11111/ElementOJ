package com.elementoj.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;

@Configuration
public class RedisConfig {
    @Bean
    public ReactiveRedisTemplate<String, OAuth2AuthorizedClient> reactiveRedisTemplate(
            ReactiveRedisConnectionFactory factory) {

        // 序列化配置
        Jackson2JsonRedisSerializer<OAuth2AuthorizedClient> serializer =
                new Jackson2JsonRedisSerializer<>(OAuth2AuthorizedClient.class);

        RedisSerializationContext<String, OAuth2AuthorizedClient> context =
                RedisSerializationContext.<String, OAuth2AuthorizedClient>newSerializationContext()
                        .key(new StringRedisSerializer())
                        .value(serializer)
                        .hashKey(new StringRedisSerializer())
                        .hashValue(serializer)
                        .build();

        return new ReactiveRedisTemplate<>(factory, context);
    }
}
