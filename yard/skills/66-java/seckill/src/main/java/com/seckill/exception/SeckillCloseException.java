/*
 * Copyright (c) 2019 study code from imooc.
 *
 * https://www.imooc.com/learn/587
 *
 * Java高并发秒杀API之业务分析与DAO层
 *
 */

package com.seckill.exception;

public class SeckillCloseException extends SeckillException {

    public SeckillCloseException(String message) {
        super(message);
    }

    public SeckillCloseException(String message, Throwable cause) {
        super(message, cause);
    }
}
