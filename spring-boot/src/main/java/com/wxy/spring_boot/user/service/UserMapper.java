package com.wxy.spring_boot.user.service;

import java.util.List;

import com.wxy.spring_boot.user.domain.User;

public interface UserMapper {    
	List<User> getAll();
    User getOne(Long id);    
    void insert(User user);    
    void update(User user);    
    void delete(Long id);

}