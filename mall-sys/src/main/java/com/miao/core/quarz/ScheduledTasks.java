package com.miao.core.quarz;

import com.miao.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时清理过期会员
 */
@Component
public class ScheduledTasks {

    @Autowired
    RedisService redisService;

    /**
     * 00:00定时任务批量删除
     */
    //@Scheduled(cron = "0 0 0 */1 * ?")
    @Scheduled(fixedRate = 5000)
    public void remove(){
        redisService.clearVip();
        System.out.println("定时任务开启");
    }

}
