package com.xxxx.springboot.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 *
 * user JavaBean
 */
@ApiModel("用户实体对象")
public class User implements Serializable {//缓存需要序列化
    @ApiModelProperty(value = "用户id",example = "0")//默认值0
    private Integer id;

    @ApiModelProperty(value = "用户名")//在线api生成
    @NotBlank(message = "用户名不能为空")//设置进行非空校验，并提示
    private String userName;


    @ApiModelProperty(value = "用户密码")
    @NotBlank(message = "用户密码不能为空")////设置进行非空校验，并提示
    @Length(min= 6, max = 10, message = "密码长度至少6位，但不能超过10位")//设置长度校验
    private String userPwd;

    @Email//设置邮件规则校验
    private String email;

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", userPwd='" + userPwd + '\'' +
                '}';
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }
}
