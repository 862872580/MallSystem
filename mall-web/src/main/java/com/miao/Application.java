package com.miao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author miaoyecheng
 * @date 2020/1/1.
 * @email 862872580@qq.com
 */

@ComponentScan("com.miao")
@MapperScan(basePackages = {"com.miao.mapper"})
@EnableScheduling //开启定时任务注解
@EnableAsync //开启异步注解
@EnableAutoConfiguration
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
        System.out.println("Server start");
    }


}
