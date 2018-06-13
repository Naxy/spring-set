package com.wxy.spring_boot.user.service;

import com.wxy.spring_boot.user.domain.UserInfo;

public interface UserInfoService {
	public UserInfo findByUsername(String username);
}
