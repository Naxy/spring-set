package com.wxy.spring_boot.user.controller;

import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wxy.spring_boot.user.domain.User;
import com.wxy.spring_boot.user.service.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserRepository userRepository;

	@RequestMapping(value = "/{username}", method = RequestMethod.GET)
	@Cacheable(value = "user-key")
	public User getUser(@PathVariable String username) {
		User user = userRepository.findByUserName(username);
		System.out.println("没用缓存...");
		return user;
	}

	@RequestMapping("/uid")
	public String uid(HttpSession session) {
		UUID uid = (UUID) session.getAttribute("uid");
		if (uid == null) {
			uid = UUID.randomUUID();
		}
		session.setAttribute("uid", uid);
		return session.getId();
	}
}
