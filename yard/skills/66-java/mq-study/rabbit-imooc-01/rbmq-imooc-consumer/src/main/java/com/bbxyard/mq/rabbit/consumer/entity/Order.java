package com.bbxyard.mq.rabbit.consumer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order implements Serializable {

    private static final long serialVersionUID = 5923078164062862L;

    private String id;

    private String name;

    /**
     * 存储消息发送的唯一标识
     */
    private String messageId;

}
