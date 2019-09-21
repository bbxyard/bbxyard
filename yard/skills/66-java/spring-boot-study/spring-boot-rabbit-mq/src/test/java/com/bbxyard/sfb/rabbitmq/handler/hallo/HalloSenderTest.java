package com.bbxyard.sfb.rabbitmq.handler.hallo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;


@SpringBootTest
@RunWith(SpringRunner.class)
public class HalloSenderTest {

    @Resource
    private HalloSender halloSender;

    @Test
    public void testHallo() throws Exception {
        halloSender.send();
    }


}