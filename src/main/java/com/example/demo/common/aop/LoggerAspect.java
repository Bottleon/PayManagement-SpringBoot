package com.example.demo.common.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggerAspect {

    @Around("execution(* com.example.demo..*Controller.*(..))||execution(* com.example.demo..*Service.*(..))")
    public Object printLog(ProceedingJoinPoint joinPoint)throws Throwable{
        String name = joinPoint.getSignature().getDeclaringTypeName();
        String type = "";

        if(name.contains("Controller")){
            type="Controller --> ";
        }else if(name.contains("Service")){
            type = "ServiceImpl --> ";
        }

        log.debug(type+name+"."+joinPoint.getSignature().getName()+"()");

        return joinPoint.proceed();
    }
}
