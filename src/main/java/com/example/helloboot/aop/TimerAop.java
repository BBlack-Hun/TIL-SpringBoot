package com.example.helloboot.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class TimerAop {

    private static final Logger LOGGER = LoggerFactory.getLogger(TimerAop.class);

    @Pointcut("execution(* com.example.helloboot.controller..*.*(..))")
    private void cut() {}


    @Pointcut("@annotation(com.example.helloboot.annotation.Timer)")
    private void enableTimer() {}

    @Around("cut() && enableTimer()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Object result = joinPoint.proceed();

        stopWatch.stop();

        LOGGER.info("total time : " + stopWatch.getTotalTimeSeconds());
    }
}
