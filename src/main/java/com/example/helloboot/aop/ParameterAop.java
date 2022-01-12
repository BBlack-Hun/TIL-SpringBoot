package com.example.helloboot.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class ParameterAop {

    private static final Logger LOGGER = LoggerFactory.getLogger(ParameterAop.class);

    @Pointcut("execution(* com.example.helloboot.controller..*.*(..))")
    private void cut() {}

    @Before("cut()")
    public void before(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        LOGGER.info(String.valueOf(method.getName()));
        Object[] args = joinPoint.getArgs();
        for(Object obj : args) {
            LOGGER.info("type : " + obj.getClass().getSimpleName());
            LOGGER.info("value : " + obj);
        }
    }

    @AfterReturning(value = "cut()", returning = "returnObj")
    public void afterReturn(JoinPoint joinPoint, Object returnObj) {
        LOGGER.info("return obj");
        LOGGER.info(returnObj.toString());
//        System.out.println(returnObj);

    }
}
