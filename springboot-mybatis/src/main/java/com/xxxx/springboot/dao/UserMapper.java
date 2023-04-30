package com.xxxx.springboot.dao;

import com.xxxx.springboot.po.User;
import com.xxxx.springboot.query.UserQuery;

import java.util.List;


/*
* dao层
* */
public interface UserMapper {
    //根据用户名查询用户记录
    User queryUserByUserName(String userName);
    //通过用户id查询用户
    User queryById(Integer id);
    //通过条件查询
    List<User> selectByParams(UserQuery userQuery);

    //添加用户
    int save(User user);
    //修改
    int update(User user);
    //删除
    int delete(Integer id);
}

