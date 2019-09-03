package com.bbxyard.mp.demo.dto;

public class HttpRespMsg {

    private int code; // 状态码

    private String desc; // 描述

    private Object data; // 负载数据

    private String errmsg; // 错误信息

    public HttpRespMsg(Object data, int code) {
        this.data = data;
        this.code = code;
    }

    public HttpRespMsg(String errmsg, int code) {
        this.errmsg = errmsg;
        this.code = code;
    }

    public static HttpRespMsg Ok(Object data) {
        return new HttpRespMsg(data, 200);
    }

    public static HttpRespMsg Error(String errmsg, int code) {
        return new HttpRespMsg(errmsg, code);
    }

    @Override
    public String toString() {
        String dataStr = data.toString();
        String s = String.format("{ \"code\": %d, \"desc\": \"%s\", \"errmsg\": \"%s\", \"data\": %s }", code, desc, errmsg, dataStr);
        return s;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
}
