package com.spring.security.msnews;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class MsNewsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsNewsApplication.class, args);
    }

}
