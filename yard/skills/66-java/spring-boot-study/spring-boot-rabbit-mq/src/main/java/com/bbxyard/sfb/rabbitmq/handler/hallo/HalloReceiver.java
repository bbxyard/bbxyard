package com.bbxyard.sfb.rabbitmq.handler.hallo;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
@RabbitListener(queues = "hallo")
public class HalloReceiver {

    @RabbitHandler
    public void process(String msg) {
        System.out.println("Consumer Recv: " + msg);
    }
}
