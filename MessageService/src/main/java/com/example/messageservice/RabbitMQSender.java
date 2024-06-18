package com.example.messageservice;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Value("${exchange}")
    private String exchange;

    public void send(GetLocationMessage message, String key) {
        rabbitTemplate.convertAndSend(exchange, key, message);
        System.out.println("Send msg = " + message);

    }
}
