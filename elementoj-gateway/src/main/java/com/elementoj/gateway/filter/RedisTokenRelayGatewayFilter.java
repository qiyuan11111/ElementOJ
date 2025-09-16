package com.elementoj.gateway.filter;

import cn.hutool.core.util.ObjectUtil;
import com.elementoj.gateway.service.TokenRefreshService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.web.server.ServerOAuth2AuthorizedClientRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class RedisTokenRelayGatewayFilter implements GatewayFilter {

    private final ServerOAuth2AuthorizedClientRepository authRepo;
    private final TokenRefreshService tokenRefreshService;

    @Value("${spring.security.oauth2.client.default-registration}")
    private String registrationId;

    public RedisTokenRelayGatewayFilter(
            ServerOAuth2AuthorizedClientRepository authRepo, TokenRefreshService tokenRefreshService) {
        this.authRepo = authRepo;
        this.tokenRefreshService = tokenRefreshService;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 这里放置原有的过滤器逻辑
        return ReactiveSecurityContextHolder.getContext()
                .map(SecurityContext::getAuthentication)
                .flatMap(authentication -> {
//                    String registrationId = "gateway";
                    return authRepo.<OAuth2AuthorizedClient>loadAuthorizedClient(
                            registrationId,
                            authentication,
                            exchange
                    );
                })
                .flatMap(tokenRefreshService::refreshIfNeeded)
                .flatMap(client -> {
                    if (ObjectUtil.isNotEmpty(client) && ObjectUtil.isNotEmpty(client.getAccessToken())) {
                        String tokenValue = client.getAccessToken().getTokenValue();
                        exchange.getRequest().mutate()
                                .header(HttpHeaders.AUTHORIZATION, "Bearer " + tokenValue)
                                .build();
                    }
                    return chain.filter(exchange);
                });
    }
}
