package com.bbxyard.mq.rabbit.producer.handler;


import com.bbxyard.mq.rabbit.producer.entity.Order;
import org.springframework.amqp.core.Correlation;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送订单
     *
     * @param order
     * @throws Exception
     */
    public void send(Order order) throws Exception {
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(order.getMessageId());

        // exchange: 交换机
        // routineKey: 路由键
        // message: 消息体内容
        // correlationData: 消息唯一ID
        this.rabbitTemplate.convertAndSend("order-exchange", "order.a", order, correlationData);
    }
}
