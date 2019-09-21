package com.bbxyard.sfb.swagger.entity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    private Long id;

    @ApiModelProperty(value = "消息体")
    private String text;

    @ApiModelProperty(value = "消息总结")
    private String summary;

    private Date createDate;
}

