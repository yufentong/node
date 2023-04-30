package com.xxxx.springboot.utils;

import com.mysql.cj.exceptions.SSLParamsException;
import com.xxxx.springboot.exceptions.ParamsException;


/*
* lang的工具类
* */
public class AssertUtil {
    /*异常提示
    * 判断结果为true ， 抛出异常，
    * */
    public static  void isTrue(Boolean flag,String msg){
        if (flag){
            throw new ParamsException(msg);//sql参数异常
        }
    }
}
