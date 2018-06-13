package com.wxy.spring_boot.docker;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/docker")
public class DockerController {
	
	
	public String index() {
		return "Hello Docker!";
	}
}
