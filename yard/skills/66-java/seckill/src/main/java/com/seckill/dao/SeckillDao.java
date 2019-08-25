/*
 * Copyright (c) 2019 study code from imooc.
 *
 * https://www.imooc.com/learn/587
 *
 * Java高并发秒杀API之业务分析与DAO层
 *
 */

package com.seckill.dao;

import com.seckill.entity.Seckill;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface SeckillDao {

    /**
     * 减库存
     *
     * @param seckillId
     * @param killTime
     * @return 更新记录行数，若<1，则更新失败
     */
    int reduceNumber(@Param("seckillId") long seckillId, @Param("killTime") Date killTime);

    /**
     * 根据id查询秒杀商品
     *
     * @param seckillId
     * @return
     */
    Seckill queryById(long seckillId);

    /**
     * 根据偏移量查询秒杀商品列表
     *
     * @param offset
     * @param limit
     * @return
     */
    List<Seckill> queryAll(@Param("offset") int offset, @Param("limit") int limit);

    /**
     * 使用存储过程执行秒杀
     *
     * @param paramMap
     */
    void killByProcedure(Map<String, Object> paramMap);

}
