package org.example.myecommerceapp.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Aspect
@Component
public class ExceptionLoggerAspect {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionLoggerAspect.class);

    @AfterThrowing(
            pointcut = "execution(* org.example.myecommerceapp.service.ProductService.*(..))")
    public void afterThrowing(JoinPoint joinPoint){
        logger.error("Service layer exception caught: ", joinPoint.getSignature(), LocalTime.now());
    }
}
