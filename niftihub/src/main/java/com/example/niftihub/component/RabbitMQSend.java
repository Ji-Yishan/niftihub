package com.example.niftihub.component;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQSend {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    private static String queueName = "message";
    public void send(String message){
        System.out.println("发送："+message);
        rabbitTemplate.convertAndSend(queueName,message);
    }

}
