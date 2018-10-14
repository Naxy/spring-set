package com.wxy.microservice.eureka.feign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/hello")
public class HelloController {


    @Autowired
    HelloService helloService;

    @RequestMapping
    public String hello(@RequestParam String name) {
        return helloService.hello( name );
    }
}