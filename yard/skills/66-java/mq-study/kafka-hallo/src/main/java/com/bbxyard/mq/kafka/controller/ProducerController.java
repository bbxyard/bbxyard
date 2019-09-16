package com.bbxyard.mq.kafka.controller;


import com.bbxyard.mq.kafka.common.ErrorCode;
import com.bbxyard.mq.kafka.common.MessageEntity;
import com.bbxyard.mq.kafka.common.Response;
import com.bbxyard.mq.kafka.producer.SimpleProducer;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/kafka")
public class ProducerController {

    @Autowired
    private SimpleProducer simpleProducer;

    @Value("${kafka.topic.default}")
    private String topic;

    private Gson gson = new Gson();


    @RequestMapping(value = "/hallo", method = RequestMethod.GET, produces = {"application/json"})
    public Response sendKafka() {
        return new Response(ErrorCode.SUCCESS, "OK");
    }

    @RequestMapping(value = "/send", method = RequestMethod.POST, produces = {"application/json"})
    public Response sendKafka(@RequestBody MessageEntity msg) {
        try {
            log.info("kafka消息={}", gson.toJson(msg));
            String key = LocalDateTime.now().toString();
            // LocalDateTime.now().format("yyyy-mm-dd HH:MM:SS");
            simpleProducer.send(topic, key, msg);
            log.info("发送kafkad成功");
            return new Response(ErrorCode.SUCCESS, "发送kafka成功");
        } catch (Exception e) {
            log.error("发送kafka失败", e);
            return new Response(ErrorCode.EXCEPTION, "发送kafka失败");
            // e.printStackTrace();
        }
    }
}
