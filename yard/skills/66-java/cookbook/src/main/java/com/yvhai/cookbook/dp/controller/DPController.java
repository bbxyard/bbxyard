package com.yvhai.cookbook.dp.controller;

import com.yvhai.cookbook.dp.ext.singleton.SingletonByDC;
import com.yvhai.cookbook.dp.ext.singleton.SingletonByEnum;
import com.yvhai.cookbook.dp.ext.singleton.SingletonByHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/dp")
public class DPController {

    @RequestMapping("singleton")
    public Object showSingleton() {
        Map<String, String> map = new HashMap<>();
        map.put("SingletonByDC", SingletonByDC.getInstance().toString());
        map.put("SingletonByEnum", SingletonByEnum.getInstance().toString());
        map.put("SingletonByHolder", SingletonByHolder.getInstance().toString());
        return map;
    }

}
