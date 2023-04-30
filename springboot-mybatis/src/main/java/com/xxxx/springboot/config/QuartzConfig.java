package com.xxxx.springboot.config;

import com.xxxx.springboot.jobs.MyFirstJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {

    //定义job实例
    @Bean
    public JobDetail jobDetail(){
        //jobBuilder:定义自定义定时任务类的jobDetail实例接口
        return JobBuilder.newJob(MyFirstJob.class).build();
    }

    //配置定时任务执行的定时器1
    @Bean
    public Trigger trigger(){
        //创建Scheduler
        SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(1)//定时任务执行时间
                .repeatForever();//默认永久执行下去
        //TriggerBuilder：定义和创建Trigger实例的接⼝--计时器配置
        return TriggerBuilder.newTrigger()
                .withIdentity("trigger1","group1")
                .withSchedule(simpleScheduleBuilder)
                .forJob(jobDetail())
                .build();
    }

    @Bean
    public Trigger trigger2() {

        //TriggerBuilder：定义和创建Trigger实例的接⼝--计时器配
        return TriggerBuilder.newTrigger()
                .withIdentity("trigger2", "group1")
                .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ? *"))
                .forJob(jobDetail())
                .build();

    }
}