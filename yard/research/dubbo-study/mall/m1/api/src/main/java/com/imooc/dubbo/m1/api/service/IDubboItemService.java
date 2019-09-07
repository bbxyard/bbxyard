package com.imooc.dubbo.m1.api.service;

import com.imooc.dubbo.m1.api.response.BaseResponse;

public interface IDubboItemService {

    BaseResponse listItems();

    BaseResponse listPageItems(Integer pageNo, Integer pageSize);

    BaseResponse search(Integer pageNo, Integer pageSize, String params);

}
