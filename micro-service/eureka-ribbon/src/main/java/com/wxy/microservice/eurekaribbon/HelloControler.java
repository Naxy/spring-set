package com.wxy.microservice.eurekaribbon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloControler {

    @Autowired
    HelloService helloService;

    @GetMapping
    public String hi(@RequestParam String name) {
        return helloService.helloService(name);
    }
}