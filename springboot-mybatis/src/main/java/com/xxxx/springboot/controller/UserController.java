package com.xxxx.springboot.controller;

import com.github.pagehelper.PageInfo;
import com.xxxx.springboot.exceptions.ParamsException;
import com.xxxx.springboot.po.User;
import com.xxxx.springboot.po.vo.ResultInfo;
import com.xxxx.springboot.query.UserQuery;
import com.xxxx.springboot.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/*
* 控制层
* @RestController是@ResponseBody和@Controller注解的组合
*   则1.表明该类控制层  2.表明返回值为数据，而不会是页面veiw
* */
@Api("用户模块")
@RestController
public class UserController {

    @Resource
    private UserService userService;


    @ApiOperation(value = "根据用户名查询用户信息")//方法的描述
    @ApiImplicitParam(name="userName",value = "查询参数",required = true,paramType = "path")//参数的描述
    @GetMapping("/user/{userName}")
    public User queryUserByUserName(@PathVariable String userName){
        System.out.println("查询参数：+++++"+userName);
        return userService.queryUserByUserName(userName);
    }


    @ApiOperation("根据用户id查询用户信息")
    @ApiImplicitParam(name = "userId",value ="查询参数",required = true,paramType = "path")
    @GetMapping("/user/get/{userId}")//对于get方法，路径层级不能相同
    public User queryUserById(@PathVariable Integer userId){
        return userService.queryUserById(userId);
    }


    @ApiOperation("通过指定参数，分页查询")
    @GetMapping("user/list")
    public PageInfo<User> list(UserQuery userQuery){//没有@Resquest注解，可以通过get方式传参
        return userService.queryUserByParams(userQuery);
    }



    @ApiOperation("添加用户")
    @ApiImplicitParam(name = "user", value = "用户实体类", dataType = "User")
    @PutMapping("/user")
    public ResultInfo save(@RequestBody User user){
        ResultInfo resultInfo = new ResultInfo();
        try{
            userService.save(user);
        }catch (ParamsException e){
            resultInfo.setCode(e.getCode());
            resultInfo.setMsg(e.getMsg());
        }catch (Exception e){
            e.printStackTrace();
            resultInfo.setCode(300);
            resultInfo.setMsg("记录添加失败！");
        }
        return resultInfo;
    }


    @ApiOperation("用户更新")
    @ApiImplicitParam(name = "user", value = "用户实体类", dataType = "User")
    @PostMapping("/user")
    public ResultInfo updateUser(@RequestBody @Valid User user){//@Valid：引入参数的验证--bean内部有校验的注解，失败会抛出异常
        ResultInfo resultInfo = new ResultInfo();
        try{
            userService.updateUser(user);
        }catch (ParamsException e){
            resultInfo.setCode(e.getCode());
            resultInfo.setMsg(e.getMsg());
        }catch (Exception e){
            e.printStackTrace();
            resultInfo.setCode(300);
            resultInfo.setMsg("记录更新失败！");
        }
        return resultInfo;
    }


    @ApiOperation("用户删除")
    @ApiImplicitParam(name = "userId", value = "查询参数",required = true, paramType = "path")
    @DeleteMapping("/user/{userId}")
    public ResultInfo deleteUser(@PathVariable("userId") Integer id){
        ResultInfo resultInfo = new ResultInfo();
        try{
            userService.deleteUser(id);
        }catch (ParamsException e){
            resultInfo.setCode(e.getCode());
            resultInfo.setMsg(e.getMsg());
        }catch (Exception e){
            e.printStackTrace();
            resultInfo.setCode(300);
            resultInfo.setMsg("记录删除失败");
        }
        return resultInfo;
    }
}

