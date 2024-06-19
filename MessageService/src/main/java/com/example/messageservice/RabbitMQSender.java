package com.example.messageservice;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Value("${exchange}")
    private String exchange;

    @Value("${routingkey}")
    private String routingkey;

    public String send(GetLocationMessage message) {
        rabbitTemplate.convertAndSend(exchange, routingkey, message);
        System.out.println("Send msg = " + message);
        return "Message <" + message + "> was send to the RabbitMQ with routing key: " + routingkey;
    }
}
