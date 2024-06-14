package com.example.newspeed.aop;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Slf4j
@Aspect
@Component
public class RequestAop {

    //컨트룰러 하위 패키지는 모두 적용
    @Around("execution(* com.example.newspeed.controller..*(..))")
    //시점
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        //다음 메서드로 진행됨
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            log.info("Request URL: " + request.getRequestURL());
            log.info("HTTP Method : " + request.getMethod());

        } else {
            log.info("인가 처리 안됨");
        }
        return joinPoint.proceed();
        }
    }

