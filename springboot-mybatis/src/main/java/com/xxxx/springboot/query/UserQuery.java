package com.xxxx.springboot.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/*
* 分页查询
* */
@ApiModel(description = "用户模块查询的条件")
public class UserQuery {
    @ApiModelProperty(value = "当前页",example = "1")
    private Integer pageNum = 1;//当前页
    @ApiModelProperty(value = "每页大小",example = "5")
    private Integer pageSize = 5; //每页显示的数量
    @ApiModelProperty(value = "用户名")
    private String userName; //查询条件，用户名

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
