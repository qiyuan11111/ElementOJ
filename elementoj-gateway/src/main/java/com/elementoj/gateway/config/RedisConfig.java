package com.elementoj.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;

@Configuration
public class RedisConfig {

    @Bean
    public ReactiveRedisTemplate<String, OAuth2AuthorizedClient> reactiveRedisTemplate(
            ReactiveRedisConnectionFactory factory) {

        RedisSerializer<String> keySerializer = new StringRedisSerializer();
        @SuppressWarnings("unchecked")
        RedisSerializer<OAuth2AuthorizedClient> valueSerializer =
                (RedisSerializer<OAuth2AuthorizedClient>) (RedisSerializer<?>)
                        new JdkSerializationRedisSerializer(RedisConfig.class.getClassLoader());

        RedisSerializationContext<String, OAuth2AuthorizedClient> context =
                RedisSerializationContext.<String, OAuth2AuthorizedClient>newSerializationContext(
                                RedisSerializationContext.SerializationPair.fromSerializer(keySerializer))
                        .value(RedisSerializationContext.SerializationPair.fromSerializer(valueSerializer))
                        .build();

        return new ReactiveRedisTemplate<>(factory, context);
    }
}