package com.elementoj;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.elementoj.module.test.mapper")
@SpringBootApplication
public class ModuleTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(ModuleTestApplication.class, args);
    }
}