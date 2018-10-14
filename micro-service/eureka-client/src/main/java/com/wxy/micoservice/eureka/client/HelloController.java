package com.wxy.micoservice.eureka.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {
    @Value("${server.port}")
    String port;

    @RequestMapping
    public String home(@RequestParam(value = "name", defaultValue = "guys") String name) {
        return "hello " + name + " ,i am from port:" + port;
    }
}
