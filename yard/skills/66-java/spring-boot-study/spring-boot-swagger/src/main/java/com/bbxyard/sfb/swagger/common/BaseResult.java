package com.bbxyard.sfb.swagger.common;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 通用响应对象
 */
@ApiModel(description = "响应对象")
@Data
public class BaseResult<T> {

    private static final int SUCCESS_CODE = 0;
    private static final String SUCCESS_MESSAGE = "成功";

    @ApiModelProperty(value = "响应码", name = "code", required = true, example = "" + SUCCESS_CODE)
    private int code;

    @ApiModelProperty(value = "响应消息", name = "msg", required = true, example = SUCCESS_MESSAGE)
    private String msg;

    @ApiModelProperty(value = "响应数据", name = "data")
    private T data;

    public BaseResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public BaseResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BaseResult() {
        this(SUCCESS_CODE, SUCCESS_MESSAGE);
    }

    public BaseResult(T data) {
        this(SUCCESS_CODE, SUCCESS_MESSAGE, data);
    }

    public static <T> BaseResult<T> success() {
        return new BaseResult<>();
    }

    public static <T> BaseResult<T> successWithData(T data) {
        return new BaseResult<>(data);
    }

    public static <T> BaseResult<T> failedWithCodeAndMsg(int code, String msg) {
        return new BaseResult<>(code, msg);
    }

    public static <T> BaseResult<T> buildWithParam(ResponseParam param) {
        return new BaseResult<>(param.getCode(), param.getMsg(), null);
    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ResponseParam {
        private int code;
        private String msg;

        public static ResponseParam buildParam(int code, String msg) {
            return new ResponseParam(code, msg);
        }
    }
}
