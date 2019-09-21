package com.bbxyard.sfb.rabbitmq.handler.hallo;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class HalloSender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send() {
        String context = "hallo " + new Date();
        System.out.println("Send: " + context);
        amqpTemplate.convertAndSend("hallo", context);
    }

}
