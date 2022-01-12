package com.example.helloboot.aop;

import com.example.helloboot.vo.UserTestVO;
import org.apache.catalina.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

@Aspect
@Component
public class DecodeAop {

    private static final Logger LOGGER = LoggerFactory.getLogger(TimerAop.class);

    @Pointcut("execution(* com.example.helloboot.controller..*.*(..))")
    private void cut() {}


    @Pointcut("@annotation(com.example.helloboot.annotation.Decode)")
    private void enableDecode() {}


    @Before("cut() && enableDecode()")
    public void before(JoinPoint joinPoint) throws UnsupportedEncodingException {
         Object[] args = joinPoint.getArgs();
         for(Object arg: args) {
             if(arg instanceof UserTestVO) {
                 UserTestVO userTestVO = UserTestVO.class.cast(arg);
                 String base64Email = userTestVO.getEmail();
                 String email = new String(Base64.getDecoder().decode(base64Email), "UTF-8");
                 userTestVO.setEmail(email);
                 LOGGER.info("이메일 변환이 완료되었습니다. (DECODE)");
             }
         }


    }

    @AfterReturning(value= "cut() && enableDecode()", returning = "returnObj")
    public void afterReturn(JoinPoint joinPoint, Object returnObj) throws UnsupportedEncodingException {
        if(returnObj instanceof UserTestVO) {
            UserTestVO userTestVO = UserTestVO.class.cast(returnObj);
            String email = userTestVO.getEmail();
            String base64Email = Base64.getEncoder().encodeToString(email.getBytes());
            userTestVO.setEmail(base64Email);
            LOGGER.info("이메일 변환이 완료되었습니다. (ENCODE)");
        }

    }
}
