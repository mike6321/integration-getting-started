package com.example.step04;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class Step04Application {

    public static void main(String[] args) {
        SpringApplication.run(Step04Application.class, args);
    }

}
