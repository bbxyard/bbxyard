package com.imooc.dubbo.one.api.service;

import com.imooc.dubbo.one.api.request.RawOrderDto;
import com.imooc.dubbo.one.api.response.BaseResponse;

public interface IDubboOrderService {

    public BaseResponse makeOrder(RawOrderDto dto);

}
