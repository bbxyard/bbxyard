/*
 * Copyright (c) 2019 study code from imooc.
 *
 * https://www.imooc.com/learn/587
 *
 * Java高并发秒杀API之业务分析与DAO层
 *
 */

package com.seckill.entity;

import java.util.Date;

public class SuccessKilled {

    private short state;        // 用户秒杀商品状态
    private Date createTime;    // 用户秒杀商品时间
    private Long seckillId;     // 秒杀商品id
    private Long userPhone;     // 秒杀用户id(手机号)

    // 多对一, 一件商品有多个库存
    private Seckill seckill;

    public short getState() {
        return state;
    }

    public void setState(short state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(Long seckillId) {
        this.seckillId = seckillId;
    }

    public Long getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(Long userPhone) {
        this.userPhone = userPhone;
    }

    public Seckill getSeckill() {
        return seckill;
    }

    public void setSeckill(Seckill seckill) {
        this.seckill = seckill;
    }

    @Override
    public String toString() {
        return "SuccessKilled{" +
                "state=" + state +
                ", createTime=" + createTime +
                ", seckillId=" + seckillId +
                ", userPhone=" + userPhone +
                ", seckill=" + seckill +
                '}';
    }
}
