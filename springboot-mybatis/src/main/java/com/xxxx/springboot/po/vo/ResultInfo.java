package com.xxxx.springboot.po.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/*
* 保存前后端传递的数据
* */
@ApiModel(description = "响应结果--model信息")
public class ResultInfo {
    @ApiModelProperty(value = "响应的状态码", example = "200")
    private Integer code = 200;
    @ApiModelProperty(value = "响应的消息结果")
    private String msg = "操作成功";
    @ApiModelProperty(value = "响应的具体结果信息")
    private Object result;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
