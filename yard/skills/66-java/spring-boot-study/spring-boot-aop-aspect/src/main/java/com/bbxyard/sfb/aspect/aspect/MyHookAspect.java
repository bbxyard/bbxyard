package com.bbxyard.sfb.aspect.aspect;


import com.bbxyard.sfb.aspect.service.MyHookService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect  // 使用@Aspect注解声明一个切面
@Component
public class MyHookAspect {

    private static int times = 0;

    @Autowired
    private MyHookService myHookService;

    @Pointcut("@annotation(com.bbxyard.sfb.aspect.anno.MyHook)")
    public void hookPointCut() {
        // System.out.println("MyHook. 钩子进来啦 - " + times);
    }

    // @Before("hookPointCut")
    // public void before(ProceedingJoinPoint point) throws Throwable {
    //     System.out.println("MyHook Before. 钩子前 - " + times);
    // }

    // @After("hookPointCut")
    // public void after(ProceedingJoinPoint point) throws Throwable {
    //     System.out.println("MyHook After. 钩子后 - " + times);
    // }
    //
    // @Around("hookPointcut")
    // public void around(ProceedingJoinPoint point) throws Throwable {
    //     System.out.println("MyHook Around - " + times);
    // }
}
