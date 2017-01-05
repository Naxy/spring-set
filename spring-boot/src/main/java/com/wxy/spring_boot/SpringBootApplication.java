package com.wxy.spring_boot;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wxy.spring_boot.thymeleaf.Person;

@Controller
@org.springframework.boot.autoconfigure.SpringBootApplication
public class SpringBootApplication {

	@RequestMapping("/")
	@ResponseBody
	public String home() {
		return "Hello World!";
	}
	@RequestMapping("/thymeleaf-index")
	public String thymeleafIndex(Model model){
		Person single = new Person("aa",2);
		List<Person> people = new ArrayList<Person>();
		Person p1 = new Person("xx",11);
		Person p2 = new Person("yy",22);
		Person p3 = new Person("zz",33);
		people.add(p1);
		people.add(p2);
		people.add(p3);
		model.addAttribute("singlePerson", single);
		model.addAttribute("people", people);
		return "thymeleaf/index";
	}
	public static void main(String[] args) throws Exception {
		SpringApplication.run(SpringBootApplication.class, args);
	}
}
