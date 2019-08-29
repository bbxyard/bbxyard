package com.imooc.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

@RestController
@RequestMapping("tasks")
public class RunTask {

    // Logger
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AsyncTask asyncTask;

    @RequestMapping("run")
    public String run() throws Exception {
        logger.info("START EXEC ASYNC TASK");
        long begin = System.currentTimeMillis();

        List<Future<Boolean>> list = new ArrayList<>();
        list.add(asyncTask.doTask1());
        list.add(asyncTask.doTask2());
        list.add(asyncTask.doTask3());

        while (true) {
            int finishedCnt = 0;
            for (Future<Boolean> b : list) {
                if (!b.isDone()) {
                    break;
                }
                finishedCnt++;
            }
            logger.debug("Try to wait");
            if (finishedCnt == list.size()) {
                break;
            }
        }

        long end = System.currentTimeMillis();

        String times = String.format("Total task cost: %dms", end - begin);
        System.out.println(times);
        logger.info(times);

        return times;
    }

}
