package com.elementoj.module.news.config;

import com.elementoj.common.security.domain.PublicPathRegister;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
public class ResourceConfiguration extends ResourceServerConfigurerAdapter {


    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId("elementoj-module-news").stateless(true);
    }


    @Bean
    public PublicPathRegister publicPathRegister() {
        return () -> Stream.of(
                "/expostulation/**"
        ).collect(Collectors.toList()).toArray(new String[0]);
    }


    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .mvcMatchers(publicPathRegister().getPublicPathList()).permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable();
    }
}
