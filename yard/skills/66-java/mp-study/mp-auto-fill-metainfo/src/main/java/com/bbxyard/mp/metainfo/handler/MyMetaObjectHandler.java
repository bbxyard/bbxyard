package com.bbxyard.mp.metainfo.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        boolean hasSetter = metaObject.hasSetter("createTime");
        if (hasSetter) {
            log.info("start insert fill ...");
            this.setFieldValByName("createTime", LocalDateTime.now(), metaObject);
            this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
            this.setFieldValByName("operator", "Jerry", metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // boolean hasUpSetter = metaObject.hasSetter("updateTime");
        // Object val = getFieldValByName("updateTime", metaObject);
        if (true) {
            log.info("start update fill ...");
            this.setUpdateFieldValByName("updateTime", LocalDateTime.now(), metaObject);
            this.setUpdateFieldValByName("operator", "Tom", metaObject);
        }
    }
}
