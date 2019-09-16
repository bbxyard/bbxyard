package com.bbxyard.mq.kafka.common;

import lombok.Data;


@Data
public class Response {

    private int code;
    private String message;

    public Response(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
