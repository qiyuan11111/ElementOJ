package com.elementoj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@EnableResourceServer
@MapperScan("com.elementoj.module.news.mapper")
@SpringBootApplication
public class ModuleJudgeApplication {
    public static void main(String[] args) {
        SpringApplication.run(ModuleJudgeApplication.class, args);
    }
}