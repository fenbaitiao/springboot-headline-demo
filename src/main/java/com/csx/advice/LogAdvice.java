package com.csx.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author 曹某
 * @version 1.0
 * description:
 */
@Component
@Aspect
@Order(6)
public class LogAdvice {

    @Before("execution(* com.csx.controller.*.*(..))")
    public void before(JoinPoint joinPoint){
        String simpleName = joinPoint.getTarget().getClass().getSimpleName();
        String name = joinPoint.getSignature().getName();
        System.out.println(simpleName+ "::" +name);
    }

}
