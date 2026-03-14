package com.elementoj.gateway.filter;

import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
public class SaveRequestWebFilter implements WebFilter {
    public static final String ORIGINAL_REQUEST_ATTR = "ORIGINAL_REQUEST_URL";
    private static final String OAUTH2_CALLBACK_PREFIX = "/login/oauth2/code";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        // 排除OAuth2回调路径
        if (isOAuth2CallbackRequest(exchange)) {
            return chain.filter(exchange);
        }

        // 保存原始请求URL
        String originalUrl = exchange.getRequest().getURI().toString();
        exchange.getAttributes().put(ORIGINAL_REQUEST_ATTR, originalUrl);

        return chain.filter(exchange);
    }

    private boolean isOAuth2CallbackRequest(ServerWebExchange exchange) {
        String path = exchange.getRequest().getPath().value();
        return path.startsWith(OAUTH2_CALLBACK_PREFIX);
    }
}
