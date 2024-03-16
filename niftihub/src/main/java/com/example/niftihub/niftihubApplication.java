package com.example.niftihub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
        (exclude = {SecurityAutoConfiguration.class})

public class niftihubApplication {

    public static void main(String[] args) {
        SpringApplication.run(niftihubApplication.class, args);
    }

}
