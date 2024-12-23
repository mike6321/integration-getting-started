package com.example.step05;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class Step05Application {

    public static void main(String[] args) {
        SpringApplication.run(Step05Application.class, args);
    }

}
