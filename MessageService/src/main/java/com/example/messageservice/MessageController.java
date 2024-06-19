package com.example.messageservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/rabbitmq/")
public class MessageController {

    RabbitMQSender rabbitMQSender;

    public MessageController(final RabbitMQSender rabbitMQSender) {
        this.rabbitMQSender = rabbitMQSender;
    }

    @GetMapping(value = "/send")
    public String producer(@RequestParam("baseId") Integer id, @RequestParam("text") String text) {
        GetLocationMessage emp=new GetLocationMessage(id, text);
        return rabbitMQSender.send(emp);
    }

}
