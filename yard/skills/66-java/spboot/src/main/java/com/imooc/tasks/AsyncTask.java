package com.imooc.tasks;

import com.imooc.pojo.SysUser;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

@Component
public class AsyncTask {

    @Async
    public Future<Boolean> doTask1() throws Exception {
        long begin = System.currentTimeMillis();
        Thread.sleep(1000);
        long end = System.currentTimeMillis();
        System.out.println("任务1耗时: " + (end - begin) + "ms");
        return new AsyncResult<>(true);
    }

    @Async
    public Future<Boolean> doTask2() throws Exception {
        long begin = System.currentTimeMillis();
        Thread.sleep(700);
        long end = System.currentTimeMillis();
        System.out.println("Task 2 costs: " + (end - begin) + "ms");
        return new AsyncResult<>(true);
    }

    @Async
    public Future<Boolean> doTask3() throws Exception {
        long begin = System.currentTimeMillis();
        Thread.sleep(600);
        long end = System.currentTimeMillis();
        System.out.println("Task 3 costs: " + (end - begin) + "ms");
        return new AsyncResult<>(true);
    }
}
