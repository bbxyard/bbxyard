package com.bbxyard.sfb.aspect.aspect;


import com.bbxyard.sfb.aspect.anno.MyHook;
import com.bbxyard.sfb.aspect.bo.MyHookBO;
import com.bbxyard.sfb.aspect.service.MyHookService;
import com.google.gson.Gson;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Aspect  // 使用@Aspect注解声明一个切面
@Component
public class MyHookAspect {

    private static int times = 0;

    private int curTimes = 0;

    @Autowired
    private MyHookService myHookService;

    @Pointcut("@annotation(com.bbxyard.sfb.aspect.anno.MyHook)")
    public void hookPointCut() {
    }

    @Before("hookPointCut()")
    public void before() throws Throwable {
        display("MyHook Before. 钩子前");
    }

    @After("hookPointCut()")
    public void after() throws Throwable {
        display("MyHook After. 钩子后");
    }

    @Around("hookPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        Object result = point.proceed();
        long endTime = System.currentTimeMillis();
        long exeTime = endTime - beginTime;
        display("MyHook Around");
        try {
            saveLog(point, exeTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    private void saveLog(ProceedingJoinPoint joinPoint, long dur) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        MyHookBO myHookBO = new MyHookBO();
        myHookBO.setExecTime(dur);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        myHookBO.setCreateDate(sdf.format(new Date()));
        MyHook myHook = method.getAnnotation(MyHook.class);
        if (myHook != null) {
            myHookBO.setRemark(myHook.value());
        }
        // 请求的 类名、方法名
        myHookBO.setClassName(joinPoint.getTarget().getClass().getName());
        myHookBO.setMethodName(signature.getName());
        // 请求的 参数
        Object[] args = joinPoint.getArgs();
        try {
            List<String> list = new ArrayList<String>();
            for (Object o : args) {
                list.add(new Gson().toJson(o));
            }
            myHookBO.setParams(list.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        myHookService.save(myHookBO);
    }

    private void display(String subject) {
        System.out.println(String.format("%s - %d / %d\n", subject, times++, curTimes++));
    }
}
