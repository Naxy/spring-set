package com.wxy.spring_boot.user.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wxy.spring_boot.user.domain.UserInfo;

public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {

	UserInfo findByUsername(String username);

}
