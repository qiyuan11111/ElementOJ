package com.elementoj.gateway.repository;

import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.web.server.ServerOAuth2AuthorizedClientRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Component
public class RedisOAuth2AuthorizedClientRepository implements ServerOAuth2AuthorizedClientRepository {
    private final ReactiveRedisOperations<String, OAuth2AuthorizedClient> redisOperations;
    private final Duration tokenTimeout = Duration.ofHours(1);

    public RedisOAuth2AuthorizedClientRepository(
           ReactiveRedisOperations<String, OAuth2AuthorizedClient> redisOperations) {
        this.redisOperations = redisOperations;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends OAuth2AuthorizedClient> Mono<T> loadAuthorizedClient(
            String clientRegistrationId,
            Authentication principal,
            ServerWebExchange exchange) {

        String key = buildKey(clientRegistrationId, principal.getName());
        return redisOperations.opsForValue().get(key)
                .map(client -> (T) client);
    }

    @Override
    public Mono<Void> saveAuthorizedClient(
            OAuth2AuthorizedClient authorizedClient,
            Authentication principal,
            ServerWebExchange exchange) {

        String key = buildKey(
                authorizedClient.getClientRegistration().getRegistrationId(),
                principal.getName()
        );

        return redisOperations.opsForValue()
                .set(key, authorizedClient, tokenTimeout)
                .then();
    }

    @Override
    public Mono<Void> removeAuthorizedClient(
            String clientRegistrationId,
            Authentication principal,
            ServerWebExchange exchange) {

        String key = buildKey(clientRegistrationId, principal.getName());
        return redisOperations.delete(key).then();
    }

    private String buildKey(String clientRegistrationId, String principalName) {
        return "oauth2:clients:" + clientRegistrationId + ":" + principalName;
    }

//    public Mono<OAuth2AuthorizedClient> refreshIfNeeded(OAuth2AuthorizedClient client) {
//        if (client.getAccessToken() != null &&
//                client.getAccessToken().getExpiresAt().isBefore(Instant.now().plus(tokenRefreshSkew))) {
//
//            if (client.getRefreshToken() != null) {
//                return refreshTokenClient.refreshToken(client)
//                        .flatMap(newClient -> {
//                            // 保存新令牌到 Redis
//                            return authorizedClientRepository.saveAuthorizedClient(
//                                    newClient,
//                                    client.getPrincipalName(),
//                                    null
//                            ).thenReturn(newClient);
//                        });
//            }
//        }
//        return Mono.just(client);
//    }
}
