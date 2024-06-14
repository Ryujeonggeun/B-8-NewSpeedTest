package com.example.newspeed.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RequestAop {

    //컨트룰러 하위 패키지는 모두 적용
    @Around("execution(* com.example.newspeed.controller..*(..))")
    //시점
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        //다음 메서드로 진행됨
        Long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());
        try {
            return joinPoint.proceed();

        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: "  + joinPoint.toString() + " " + timeMs + "ms");
        }
    }
}
