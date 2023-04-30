package com.xxxx.springboot.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/*
* 定时任务：设置到这里
* 1.实现Job接口
* 2.重写execute方法，调用要执行的任务
* */
public class MyFirstJob implements Job {
    //单独引入要使用日志的类
    private Logger logger = LoggerFactory.getLogger(MyFirstJob.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //声明要定时执行的任务
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        logger.info(sdf.format(new Date())+"--->定时任务执行");
    }
}
