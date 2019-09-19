package com.bbxyard.sfb.aspect.bo;


import lombok.Data;

@Data
public class MyHookBO {

    private String className;

    private String methodName;

    private String params;

    private String execTime;

    private String remark;

    private String createDate;

}
