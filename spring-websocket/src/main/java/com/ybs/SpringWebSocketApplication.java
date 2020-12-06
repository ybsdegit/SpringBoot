package com.ybs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SpringWebSocketApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringWebSocketApplication.class, args);
    }

}
