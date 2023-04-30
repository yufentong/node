package com.xxxx.springboot.exceptions;


import com.xxxx.springboot.po.vo.ResultInfo;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 捕获全局中的异常的类
 *
 */
@ControllerAdvice//控制层异常的通知（操作）
public class ClobalExceptionHandler {
    /*
    * 全局异常的处理 返回信息类（model）--》需要json格式
    * */
    @ExceptionHandler(value = Exception.class)//捕获异常，类型Exception
    @ResponseBody
    public ResultInfo exceptionHandler(Exception e){
        //捕获到异常的处理
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setCode(300);
        resultInfo.setMsg("操作失败");
        //对不同的异常分类处理
        if (e instanceof ParamsException){
            ParamsException pe = (ParamsException) e;
            resultInfo.setCode(pe.getCode());
            resultInfo.setMsg(pe.getMsg());
        }else if(e instanceof BindException){//参数异常捕获和处理
            BindException be = (BindException) e;
            resultInfo.setResult(be.getBindingResult().getFieldError().getDefaultMessage());
        }
        return resultInfo;
    }

    @ExceptionHandler(value = NoLoginException.class)
    @ResponseBody
    public ResultInfo userNotLoginHandler(NoLoginException authException){
        System.out.println("用户登录异常的处理。。。。");
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setCode(authException.getCode());
        resultInfo.setMsg(authException.getMsg());
        return resultInfo;
    }
}
