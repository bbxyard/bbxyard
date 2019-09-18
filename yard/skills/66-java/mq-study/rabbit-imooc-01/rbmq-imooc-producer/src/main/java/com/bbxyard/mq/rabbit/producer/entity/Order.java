package com.bbxyard.mq.rabbit.producer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order implements Serializable {

    private static final long serialVersionUID = 14159265358975953L;

    private String id;

    private String name;

    private String messageId;

}
