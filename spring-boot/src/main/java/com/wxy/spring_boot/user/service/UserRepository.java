package com.wxy.spring_boot.user.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wxy.spring_boot.user.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUserName(String userName);

	User findByUserNameOrEmail(String username, String email);
	
	User save(User user);
}