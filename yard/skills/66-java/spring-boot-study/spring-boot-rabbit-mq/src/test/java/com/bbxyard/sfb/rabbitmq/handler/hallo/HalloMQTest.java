package com.bbxyard.sfb.rabbitmq.handler.hallo;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


@SpringBootTest
@RunWith(SpringRunner.class)
public class HalloMQTest {

    @Test
    public void test() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setPort(5672);
        factory.setVirtualHost("/");
        factory.setUsername("hero");
        factory.setPassword("1949.cn");
        Connection clinet = factory.newConnection();
        System.out.println(clinet);
    }

}


