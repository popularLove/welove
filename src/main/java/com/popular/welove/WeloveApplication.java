package com.popular.welove;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class, scanBasePackages = {"com.popular.welove"})
@MapperScan("com.popular.welove.mapper")
@EnableScheduling
@EnableCaching
public class WeloveApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeloveApplication.class, args);
    }

}
