package com.imooc.config;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogServiceTakeTime {

    final static Logger logger = LoggerFactory.getLogger(LogServiceTakeTime.class);


    @Pointcut("execution(* com.imooc.service..*.*(..))")
    public void performance() {
        System.out.println("performance hooked");
    }

    @Around("performance()")
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
        // 记录起始时间
        long begin = System.currentTimeMillis();
        Object result = "";
        // 执行目标方法
        try {
            result = joinPoint.proceed();
        } catch (Exception e) {
            // e.printStackTrace();
            logger.error("日志记录错误, errorMessage: {}", e.getMessage());
        } finally {
            // 记录操作时间
            long took = (System.currentTimeMillis() - begin) / 1000;
            if (took >= 10) {
                logger.error("Service 执行时间为: {}秒", took);
            } else if (took >= 5) {
                logger.warn("Service 执行时间为: {}秒", took);
            } else  if (took >= 2) {
                logger.info("Service执行时间为: {}秒", took);
            }
        }
        return result;
    }

    @Before("performance()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 接收到请求, 记录请求内容
        logger.info("doBefore");
    }
}
