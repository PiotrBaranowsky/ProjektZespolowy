package com.example.messageservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/rabbitmq/")
public class MessageController {

    RabbitMQSender rabbitMQSender;

    public MessageController(final RabbitMQSender rabbitMQSender) {
        this.rabbitMQSender = rabbitMQSender;
    }
    @CrossOrigin(origins = "*")
    @GetMapping(value = "/send")
    public String producer(@RequestParam("baseId") Integer id, @RequestParam("text") String text) {
        GetLocationMessage emp=new GetLocationMessage(id, text);
        return rabbitMQSender.send(emp);
    }

}
