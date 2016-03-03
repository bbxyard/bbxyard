package com.bbxyard.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Util {
    public static HashMap<String, String> parseStringParams(String params) {
        HashMap<String, String> map = new HashMap<String, String>();
        String[] attrs = params.split(";");
        for (String attr: attrs) {
            String[] sub = attr.split("=");
            if (sub.length < 2)
                continue;
            map.put(sub[0], sub[1]);
        }
        return map;
    }

    public static String getOrDefaultFromHashMap(HashMap<String, String> map, String key, String defValue) {
        // jdk1.8+ 支持 getOrDefault
        // jdk1.7 兼容自己山寨一个.
        String value = map.get(key);
        return (value != null)? value: defValue;
    }
	
	// e.g. sfmt "yyyy-MM-dd HH:mm:ss"
    public static String getCurrentDataTime(String sfmt) {
        // 格式化当前日期时间
        Calendar c1 = Calendar.getInstance();
        c1.setTime(new Date());
        SimpleDateFormat sdf = new SimpleDateFormat(sfmt);
        String now = sdf.format(c1.getTime());
        return now;
    }
    
    public static <K,V> Map<K,V> newHashMap() { 
        return new HashMap<K,V>();  
    } 

//    public static String getClientAddr(HttpServletRequest request) {
//        String ip = request.getHeader("x-forwarded-for");
//        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getHeader("Proxy-Client-IP");
//        }
//        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getHeader("WL-Proxy-Client-IP");
//        }
//        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getRemoteAddr();
//        }
//        return ip;
//    }	
}
