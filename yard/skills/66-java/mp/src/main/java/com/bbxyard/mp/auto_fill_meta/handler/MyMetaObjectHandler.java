package com.bbxyard.mp.auto_fill_meta.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    private static final Logger logger = LoggerFactory.getLogger(MyMetaObjectHandler.class);

    @Override
    public void insertFill(MetaObject metaObject) {
        logger.info("start to insert fill ...");
        this.setFieldValByName("operator", "Alice", metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        logger.info("start to update fill ...");
        this.setFieldValByName("operator", "Bob", metaObject);
    }
}
