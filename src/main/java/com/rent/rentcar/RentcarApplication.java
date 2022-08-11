package com.rent.rentcar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)

public class RentcarApplication {
    public static void main(String[] args) {
        SpringApplication.run(RentcarApplication.class, args);
    }
}


