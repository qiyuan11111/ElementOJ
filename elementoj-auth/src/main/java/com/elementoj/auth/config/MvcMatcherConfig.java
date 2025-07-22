package com.elementoj.auth.config;

import org.springframework.util.AntPathMatcher;

public class MvcMatcherConfig {
    public static void main(String[] args) {
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        System.out.println(antPathMatcher.match("/problem/**", "/problem/1"));
    }
}
