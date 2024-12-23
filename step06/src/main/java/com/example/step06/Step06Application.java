package com.example.step06;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class Step06Application {

    public static void main(String[] args) {
        SpringApplication.run(Step06Application.class, args);
    }

}
