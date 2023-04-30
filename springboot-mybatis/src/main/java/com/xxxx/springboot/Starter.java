package com.xxxx.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication//表明着是程序的启动入口
@EnableCaching//启用缓存
@MapperScan("com.xxxx.springboot.dao")//mybatis扫描对应的dao层接口
public class Starter {
    public static void main(String[] args) {
        SpringApplication.run(Starter.class);//设置spring的启动类为入口
    }
}
