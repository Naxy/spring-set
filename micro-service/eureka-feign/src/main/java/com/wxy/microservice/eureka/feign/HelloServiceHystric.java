package com.wxy.microservice.eureka.feign;

import org.springframework.stereotype.Component;

@Component
public class HelloServiceHystric implements HelloService{
    @Override
    public String hello(String name) {
        return  "Hystric, error";
    }
}
