package com.bbxyard.mq.rabbit.producer.handler;

import com.bbxyard.mq.rabbit.producer.entity.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.UUID;

import static org.junit.Assert.*;


@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderSenderTest {

    @Resource
    private OrderSender orderSender;

    @Test
    public void testSend1() throws Exception {
        Order order = new Order();
        order.setId("201909181101360315");
        order.setName("测试订单-1");
        order.setMessageId(System.currentTimeMillis() + "$" + UUID.randomUUID().toString().replaceAll("-", ""));
        this.orderSender.send(order);
    }

}