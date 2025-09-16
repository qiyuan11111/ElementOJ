package com.elementoj.gateway.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.web.server.ServerOAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2RefreshToken;
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.Instant;
import java.util.Collections;

@Service
//@Slf4j
public class TokenRefreshService {
    // 令牌刷新时间偏移量（提前5分钟刷新）
    private static final Duration TOKEN_REFRESH_SKEW = Duration.ofMinutes(5);

    private final WebClient webClient;
    private final ServerOAuth2AuthorizedClientRepository authorizedClientRepository;

    public TokenRefreshService(
            ServerOAuth2AuthorizedClientRepository authorizedClientRepository,
            WebClient.Builder webClientBuilder) {

        this.authorizedClientRepository = authorizedClientRepository;
        this.webClient = webClientBuilder.build();
    }

    /**
     * 检查并在需要时刷新令牌
     *
     * @param client 授权的客户端
     * @return 刷新后的客户端或原始客户端
     */
    public Mono<OAuth2AuthorizedClient> refreshIfNeeded(OAuth2AuthorizedClient client) {
        if (shouldRefresh(client)) {
//            logger.debug("Token needs refresh for client: {}", client.getPrincipalName());
            return refreshToken(client)
                    .flatMap(newClient ->
                            saveAuthorizedClient(newClient, client.getPrincipalName())
                                    .thenReturn(newClient)
                    )
                    .onErrorResume(e -> {
//                        logger.error("Token refresh failed for {}: {}",
//                                client.getPrincipalName(), e.getMessage());
                        return Mono.just(client); // 返回原始客户端作为回退
                    });
        }
        return Mono.just(client);
    }

    /**
     * 判断是否需要刷新令牌
     */
    private boolean shouldRefresh(OAuth2AuthorizedClient client) {
        if (client == null) return false;

        OAuth2AccessToken accessToken = client.getAccessToken();
        if (accessToken == null) return false;

        Instant expiresAt = accessToken.getExpiresAt();
        if (expiresAt == null) return false;

        // 检查令牌是否即将过期
        boolean isExpiringSoon = expiresAt.isBefore(Instant.now().plus(TOKEN_REFRESH_SKEW));
        boolean hasRefreshToken = client.getRefreshToken() != null;

        return isExpiringSoon && hasRefreshToken;
    }

//    private MultiValueMap<String, String> createRefreshFormData(OAuth2AuthorizedClient client) {
//        ClientRegistration registration = client.getClientRegistration();
//        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
//
//        formData.add("grant_type", "refresh_token");
//        formData.add("refresh_token", client.getRefreshToken().getTokenValue());
//        formData.add("client_id", registration.getClientId());
//        formData.add("client_secret", registration.getClientSecret());
//
//        if (registration.getScopes() != null && !registration.getScopes().isEmpty()) {
//            formData.add("scope", String.join(" ", registration.getScopes()));
//        }
//
//        return formData;
//    }

    /**
     * 刷新令牌
     */
    private Mono<OAuth2AuthorizedClient> refreshToken(OAuth2AuthorizedClient client) {
        ClientRegistration registration = client.getClientRegistration();
        OAuth2RefreshToken refreshToken = client.getRefreshToken();

        if (refreshToken == null) {
            return Mono.error(new IllegalStateException("No refresh token available"));
        }

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("grant_type", "refresh_token");
        formData.add("refresh_token", refreshToken.getTokenValue());
        formData.add("client_id", registration.getClientId());
        formData.add("client_secret", registration.getClientSecret());

        // Add scope parameter if present in original registration
        if (registration.getScopes() != null && !registration.getScopes().isEmpty()) {
            formData.add("scope", String.join(" ", registration.getScopes()));
        }

        String tokenUri = registration.getProviderDetails().getTokenUri();
//        logger.debug("Refreshing token at: {}", tokenUri);

        return webClient.post()
                .uri(tokenUri)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromFormData(formData))
                .retrieve()
                .onStatus(HttpStatusCode::isError, response ->
                                response.bodyToMono(String.class)
                                        .flatMap(body -> {
                                            String errorMsg = String.format("Token refresh failed: %d - %s",
                                                    response.statusCode().value(), body);
//                                    logger.error(errorMsg);
                                            return Mono.<Throwable>error(new Exception(errorMsg));
                                        })
                )
                .bodyToMono(OAuth2AccessTokenResponse.class)
                .map(response -> createNewAuthorizedClient(client, response));
//                .doOnNext(newClient -> logger.info("New token issued for: {}", client.getPrincipalName()));
    }

    /**
     * 创建新的授权客户端
     */
    private OAuth2AuthorizedClient createNewAuthorizedClient(
            OAuth2AuthorizedClient originalClient,
            OAuth2AccessTokenResponse tokenResponse) {

        return new OAuth2AuthorizedClient(
                originalClient.getClientRegistration(),
                originalClient.getPrincipalName(),
                tokenResponse.getAccessToken(),
                tokenResponse.getRefreshToken() != null ?
                        tokenResponse.getRefreshToken() : originalClient.getRefreshToken()
        );
    }

    /**
     * 保存新的授权客户端到仓库
     */
    private Mono<Void> saveAuthorizedClient(
            OAuth2AuthorizedClient client, String principalName) {

        return authorizedClientRepository.saveAuthorizedClient(
                client,
                createAuthentication(principalName),
                null
        );
    }

    /**
     * 创建简单的认证对象
     */
    private org.springframework.security.core.Authentication createAuthentication(String principalName) {
        return new SimpleAuthentication(principalName);
    }

    /**
     * 简单的认证实现
     */
    private record SimpleAuthentication(
            String principalName) implements org.springframework.security.core.Authentication {

        @Override
        public String getName() {
            return principalName;
        }

        @Override
        public Object getPrincipal() {
            return principalName;
        }

        // 其他必要方法的实现
        @Override
        public boolean isAuthenticated() {
            return true;
        }

        @Override
        public void setAuthenticated(boolean isAuthenticated) {
        }

        @Override
        public Object getCredentials() {
            return null;
        }

        @Override
        public Object getDetails() {
            return null;
        }

        @Override
        public java.util.Collection<? extends org.springframework.security.core.GrantedAuthority> getAuthorities() {
            return Collections.emptyList();
        }
    }
}
