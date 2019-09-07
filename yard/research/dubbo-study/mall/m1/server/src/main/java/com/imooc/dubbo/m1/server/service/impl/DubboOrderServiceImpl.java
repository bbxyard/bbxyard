package com.imooc.dubbo.m1.server.service.impl;

import com.imooc.dubbo.m1.api.request.RawOrderDto;
import com.imooc.dubbo.m1.api.response.BaseResponse;
import com.imooc.dubbo.m1.api.service.IDubboOrderService;
import org.springframework.stereotype.Service;

@Service
public class DubboOrderServiceImpl implements IDubboOrderService {
    @Override
    public BaseResponse makeOrder(RawOrderDto dto) {
        return null;
    }
}
