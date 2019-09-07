package com.imooc.dubbo.m1.server.service.impl;

import com.imooc.dubbo.m1.api.response.BaseResponse;
import com.imooc.dubbo.m1.api.service.IDubboItemService;
import org.springframework.stereotype.Service;

@Service
public class DubboItemServiceImpl implements IDubboItemService {
    @Override
    public BaseResponse listItems() {
        return null;
    }

    @Override
    public BaseResponse listPageItems(Integer pageNo, Integer pageSize) {
        return null;
    }

    @Override
    public BaseResponse search(Integer pageNo, Integer pageSize, String params) {
        return null;
    }
}
