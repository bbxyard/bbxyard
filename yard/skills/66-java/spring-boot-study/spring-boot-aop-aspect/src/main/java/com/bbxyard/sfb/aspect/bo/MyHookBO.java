package com.bbxyard.sfb.aspect.bo;


import lombok.Data;

@Data
public class MyHookBO {

    private String className;

    private String methodName;

    private String params;

    private Long execTime;

    private String remark;

    private String createDate;

}
