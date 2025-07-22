package com.elementoj.common.security.utils;

import com.elementoj.api.system.domain.EleUser;
import com.elementoj.common.security.domain.PublicPathRegister;
import com.elementoj.common.security.filter.IgnoreAuthorizationFilter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class SecurityUtils {
    public static String encryptPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    public static EleUser getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (EleUser) authentication.getPrincipal();
    }

    public static HttpSecurity handPublicPathHttpSecurity(HttpSecurity http, PublicPathRegister publicPathRegister) throws Exception {
        http.addFilterBefore(new IgnoreAuthorizationFilter(publicPathRegister), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .mvcMatchers(publicPathRegister.getPublicPathList()).permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable();
        return http;
    }
}
