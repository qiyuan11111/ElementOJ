package com.elementoj.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE + 1) // 较低优先级
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)  // 在这里禁用CSRF
                .formLogin(login -> login
                        .loginPage("/login-view")   // 指定登录页面URL
                        .loginProcessingUrl("/login")
                        .permitAll()
                )
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/register", "/error", "/login", "/").permitAll()
                        .requestMatchers("/.well-known/**", "/**.ico").permitAll() // 放行 .json 和 .ico
                        .anyRequest().authenticated()
                );
        // 启用纯安全过滤器登录页（不经过MVC）
        return http.build();
    }

}
