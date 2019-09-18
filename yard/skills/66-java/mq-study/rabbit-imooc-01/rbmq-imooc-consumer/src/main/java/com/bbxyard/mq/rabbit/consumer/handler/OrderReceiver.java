package com.bbxyard.mq.rabbit.consumer.handler;


import com.bbxyard.mq.rabbit.consumer.entity.Order;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class OrderReceiver {

    /**
     * 接收消息
     *
     * @param order 消息体内容
     * @param headers 消息头内容
     * @param channel 网络信道
     * @throws IOException
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "order-queue", durable = "true"),
            exchange = @Exchange(name = "order-exchange", type = "topic"),
            key = "order.*"
    ))
    @RabbitHandler
    public void onOrderMessage(@Payload Order order, @Headers Map<String, Object> headers, Channel channel) throws IOException {
        // 消费者操作
        System.out.println("收到消息: ");
        System.out.println("订单信息: " + order.toString());

        // 手动签收消息
        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        channel.basicAck(deliveryTag, false);
    }

}
