package com.wxy.spring_boot.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxy.spring_boot.user.domain.UserInfo;

@Service
public class UserInfoServiceImpl implements UserInfoService{

	@Autowired
	UserInfoRepository userInfoRepository;
	
	public UserInfo findByUsername(String username){
		return userInfoRepository.findByUsername(username);
	}
}
