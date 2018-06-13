package com.wxy.spring_boot.user.service;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.wxy.spring_boot.user.domain.User;
import com.wxy.spring_boot.user.domain.UserSexEnum;

public interface UserMapper2 {    
    @Select("SELECT * FROM user")     
    @Results({        
       @Result(property = "userSex",  column = "user_sex"),        
       @Result(property = "nickName", column = "nick_name")
    })   
    List<User> getAll();
    
    @Select("SELECT * FROM user WHERE id = #{id}")
    @Results({        
        @Result(property = "userSex",  column = "user_sex"),
        @Result(property = "nickName", column = "nick_name")
    })    
    User getOne(Long id);    
    
    @Insert("INSERT INTO user(user_Name,pass_Word,user_Sex) VALUES(#{userName}, #{passWord}, #{userSex})")    
    void insert(User user);    
    
    @Update("UPDATE user SET user_Name=#{userName},nick_name=#{nickName} WHERE id =#{id}")
    void update(User user);    
    
    @Delete("DELETE FROM user WHERE id =#{id}")    
    void delete(Long id);

}