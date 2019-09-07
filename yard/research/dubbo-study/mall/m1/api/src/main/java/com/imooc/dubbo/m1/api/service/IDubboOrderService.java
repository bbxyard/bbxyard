package com.imooc.dubbo.m1.api.service;

import com.imooc.dubbo.m1.api.request.RawOrderDto;
import com.imooc.dubbo.m1.api.response.BaseResponse;

public interface IDubboOrderService {

    BaseResponse makeOrder(RawOrderDto dto);

}
