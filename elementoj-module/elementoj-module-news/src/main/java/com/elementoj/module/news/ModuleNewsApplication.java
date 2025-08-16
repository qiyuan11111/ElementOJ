package com.elementoj.module.news;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScans({
        @MapperScan("com.elementoj.**.mapper"),
        @MapperScan("com.elementoj.**.mapbean")
})
@SpringBootApplication
public class ModuleNewsApplication {
    public static void main(String[] args) {
        SpringApplication.run(ModuleNewsApplication.class, args);
    }
}
