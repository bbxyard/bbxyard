package com.bbxyard.study.hutool;

import cn.hutool.core.lang.ObjectId;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class IdUtilTest {

    @Test
    public void testIDGen() {
        Map<String, String> map = new HashMap<>();

        // UUID
        map.put("uuid", IdUtil.randomUUID()); // 带"-"
        map.put("uuid-no-dash", IdUtil.simpleUUID());

        // ObjectID
        map.put("ObjectId", ObjectId.next());
        map.put("ObjectId-2", IdUtil.objectId());

        // twitter snowflake
        Snowflake snowflake = IdUtil.createSnowflake(1, 1);
        map.put("snowflake", snowflake.nextIdStr());

        System.out.println(map);
    }

}
