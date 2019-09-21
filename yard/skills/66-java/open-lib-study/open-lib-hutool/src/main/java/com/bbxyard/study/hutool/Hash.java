package com.bbxyard.study.hutool;

import cn.hutool.crypto.SecureUtil;

import java.util.HashMap;
import java.util.Map;

public class Hash {

    public Map<String, String> calc(String msg) {
        Map<String, String> map = new HashMap<>();
        map.put("md5", SecureUtil.md5(msg));
        map.put("sha1", SecureUtil.sha1(msg));
        map.put("sha256", SecureUtil.sha256(msg));
        return map;
    }

}
