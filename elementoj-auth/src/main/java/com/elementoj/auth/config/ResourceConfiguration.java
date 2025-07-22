package com.elementoj.auth.config;


import com.elementoj.common.security.domain.PublicPathRegister;
import com.elementoj.common.security.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

import javax.annotation.Resource;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
public class ResourceConfiguration extends ResourceServerConfigurerAdapter {
    @Resource
    public PublicPathRegister publicPathRegister;
    @Value("${spring.application.name}")
    private String applicationName;

    @Bean
    public PublicPathRegister publicPathRegister() {
        return () -> Stream.of(
                "/oauth/token",
                "/oauth/check_token",
                "/oauth/token_key",
                "/login",
                "/register",
                "/test"
        ).collect(Collectors.toList()).toArray(new String[0]);
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId("elementoj-auth").stateless(true);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        SecurityUtils.handPublicPathHttpSecurity(http, publicPathRegister);
    }
}
