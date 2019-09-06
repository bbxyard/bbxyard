package com.imooc.dubbo.one.api.service;

import com.imooc.dubbo.one.api.response.BaseResponse;

public interface IDubboItemService {

    BaseResponse listItems();

    BaseResponse listPageItems(Integer pageNo, Integer pageSize);

    BaseResponse search(Integer pageNo, Integer pageSize, String params);

}
