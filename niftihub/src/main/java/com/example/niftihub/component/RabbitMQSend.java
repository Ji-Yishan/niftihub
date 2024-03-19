package com.example.niftihub.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RabbitMQSend {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    private static String queueName = "message";
    public void send(String message){
        log.info("发送消息到消息队列");
        rabbitTemplate.convertAndSend(queueName,message);
    }

}
