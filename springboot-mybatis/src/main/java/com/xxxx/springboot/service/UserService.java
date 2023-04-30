package com.xxxx.springboot.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxxx.springboot.dao.UserMapper;
import com.xxxx.springboot.exceptions.NoLoginException;
import com.xxxx.springboot.po.User;
import com.xxxx.springboot.query.UserQuery;
import com.xxxx.springboot.utils.AssertUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/*
缓存：查询--》@Cacheable(value="缓存区"，key="键")
     删除--》@CacheEvict
     新增，修改--》@CachePut
* service层
* */
@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    //根据用户名查询用户信息
    @Cacheable(value = "users", key = "#userName")
    public User queryUserByUserName(String userName){
        User user = userMapper.queryUserByUserName(userName);
        return user;
    }

    //根据用户id查询用户信息
    @Cacheable(value = "users", key = "#userId")
    //将数据放到users的缓存区的userId键下[key一般的为参数的组合，key"-"key]
    public User queryUserById(Integer id){
        //抛出异常
        AssertUtil.isTrue(true,"异常测试.......");
        return userMapper.queryById(id);
    }
    //通过参数查询，并分页
    @Cacheable(value = "users", key = "#userQuery.userName+'-'+#userQuery.pageNum+'-'+#userQuery.pageSize")
    public PageInfo<User> queryUserByParams(UserQuery userQuery){
        //设置分页的起始
        PageHelper.startPage(userQuery.getPageNum(),userQuery.getPageSize());
        //查询分页记录保存在页面
        return new PageInfo<>(userMapper.selectByParams(userQuery));
    }
    //添加用户的操作
    @CachePut(value = "users", key = "#user.id")
    public void save(User user){
        //登录异常测试
        if (1 ==1){
            throw new NoLoginException();
        }
        //参数非空判断
        AssertUtil.isTrue(StringUtils.isBlank(user.getUserName()),"用户名不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(user.getUserPwd()),"密码不能为空");
        AssertUtil.isTrue(user.getId() == null,"找不到用户");
        User temp = userMapper.queryUserByUserName(user.getUserName());
       //判断用户是否存在
        AssertUtil.isTrue(null != temp,"用户已存在");
        AssertUtil.isTrue(userMapper.save(user) < 1,"用户记录添加失败");
    }

    //修改用户
    @Transactional//标注当前方法，需要事务管理
    @CachePut(value = "users", key = "#user.id")
    public void updateUser(User user){
        //参数非空判断
        AssertUtil.isTrue(StringUtils.isBlank(user.getUserName()),"用户名不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(user.getUserPwd()),"用户密码不能为空");
        //判断用户是否存在
        User temp = userMapper.queryUserByUserName(user.getUserName());
        AssertUtil.isTrue(null != temp && !(temp.getId().equals(user.getId())),"该用户已存在在！");
        AssertUtil.isTrue(userMapper.update(user) < 1,"用户记录更新失败");
    }

    //删除用户
    @CacheEvict(value = "users", allEntries = true)
    public void  deleteUser(Integer id){
        //判断要删除的用户是否存在
        AssertUtil.isTrue(null == id || null == userMapper.queryById(id),"待删除用户不存在");
        //删除用户
        AssertUtil.isTrue(userMapper.delete(id) < 1,"用户删除失败");
    }

}
