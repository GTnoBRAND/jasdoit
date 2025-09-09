package org.example.myecommerceapp.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class ProductAspect {

    @Around(value = "execution(* org.example.myecommerceapp.rest.*.*(..))")
    public Object around(ProceedingJoinPoint joinPoint)throws Throwable {
        Object result = joinPoint.proceed();
        String className = "CLASS: [" + joinPoint.getTarget().getClass().getSimpleName()+ "],";
        String methodName = "METHOD: [" + joinPoint.getSignature().getName() + "],";
        System.out.println(className + methodName + "REQUEST: ");
        if(joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
            Arrays.stream(joinPoint.getArgs()).forEach(System.out::println);
        } else {
            System.out.println("[]");
        }

        Long startTime = System.currentTimeMillis();
        try{
            result = joinPoint.proceed();
            Long endTime = System.currentTimeMillis();

            System.out.println(className +methodName + "RESPONSE: "+ result);
            System.out.println(className +methodName + "RESPONSE: "+ (endTime - startTime) + "ms");
            return result;
        }catch(Throwable e){
            System.err.println(className + ", "+  methodName + "- EXCEPTION: "+ e.getMessage());
            throw e;
        }
    }
}