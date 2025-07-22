package com.elementoj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@EnableFeignClients
@EnableResourceServer
@MapperScan("com.elementoj.module.solution.mapper")
@SpringBootApplication
public class ModuleSolutionApplication {
    public static void main(String[] args) {
        SpringApplication.run(ModuleSolutionApplication.class, args);
    }
}