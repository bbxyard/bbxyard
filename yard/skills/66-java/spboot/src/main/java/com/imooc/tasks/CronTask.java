package com.imooc.tasks;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class CronTask {


    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 3000)
    public void taskA() {
        // System.out.println("[每3秒执行]现在时间: " + dateFormat.format(new Date()));
    }

    @Scheduled(cron="4-40 * * * * ?")
    public void testB() {
        // System.out.println("[每4-40秒执行]现在时间: " + dateFormat.format(new Date()));
    }
}
