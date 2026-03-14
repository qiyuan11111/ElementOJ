package com.elementoj.gateway.handler;

import com.elementoj.gateway.filter.SaveRequestWebFilter;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;

@Component
public class RedirectAuthenticationSuccessHandler implements ServerAuthenticationSuccessHandler {
    @Override
    public Mono<Void> onAuthenticationSuccess(WebFilterExchange webFilterExchange,
                                              Authentication authentication) {
        ServerWebExchange exchange = webFilterExchange.getExchange();

        // 1. 获取保存的原始请求URL
        String originalUrl = exchange.getAttribute(SaveRequestWebFilter.ORIGINAL_REQUEST_ATTR);

        // 2. 处理未保存URL的情况
        if (originalUrl == null || originalUrl.isBlank()) {
            return redirectToDefaultPage(exchange);
        }

        // 3. 防止重定向循环
        if (isAuthPath(originalUrl)) {
            return redirectToDefaultPage(exchange);
        }

        // 4. 执行重定向
        return redirectToOriginalUrl(exchange, originalUrl);
    }

    private Mono<Void> redirectToOriginalUrl(ServerWebExchange exchange, String url) {
        exchange.getResponse().setStatusCode(HttpStatus.SEE_OTHER);
        exchange.getResponse().getHeaders().setLocation(URI.create(url));
        return exchange.getResponse().setComplete();
    }

    private Mono<Void> redirectToDefaultPage(ServerWebExchange exchange) {
        return redirectToOriginalUrl(exchange, "/");
    }

    private boolean isAuthPath(String url) {
        return url.contains("/login") || url.contains("/oauth2");
    }
}
