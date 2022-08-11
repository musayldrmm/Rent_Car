package com.rent.rentcar.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceAspect {
    @Before("execution(* com.rent.rentcar.service.CustomerService.findAllCustomer(..))")
    public void beforeLogin(JoinPoint joinPoint) {
        System.out.println("login öncesi işlem yapıldı");
    }

    @After("execution(* com.rent.rentcar.service.CustomerService.findAllCustomer(..))")
    public void afterLogin(JoinPoint joinPoint) {
        System.out.println("login sonrası işlem yapıldı");
    }
}
