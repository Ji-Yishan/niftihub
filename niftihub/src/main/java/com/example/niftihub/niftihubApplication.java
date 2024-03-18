package com.example.niftihub;

import com.example.niftihub.component.RabbitMQSend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
        (exclude = {SecurityAutoConfiguration.class})
@EnableScheduling//定时任务
public class niftihubApplication {
    public static void main(String[] args) {
        SpringApplication.run(niftihubApplication.class, args);
    }

}
