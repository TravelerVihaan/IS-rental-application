package com.github.vihaan.isrentalapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class IsRentalApplication {

    public static void main(String[] args) {
        SpringApplication.run(IsRentalApplication.class, args);
    }

}
