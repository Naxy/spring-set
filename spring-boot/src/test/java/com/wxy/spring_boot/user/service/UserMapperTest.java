package com.wxy.spring_boot.user.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wxy.spring_boot.user.domain.User;
import com.wxy.spring_boot.user.domain.UserSexEnum;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {    
    @Autowired
    private UserMapper UserMapper;    
    @Test
    public void testInsert() throws Exception {
        UserMapper.insert(new User("aa", "a123456", UserSexEnum.MAN.getValue()));
        UserMapper.insert(new User("bb", "b123456", UserSexEnum.WOMAN.getValue()));
        UserMapper.insert(new User("cc", "b123456", UserSexEnum.WOMAN.getValue()));

        Assert.assertEquals(3, UserMapper.getAll().size());
    }   
   
    @Test
    public void testQuery() throws Exception {
        List<User> users = UserMapper.getAll();
        System.out.println(users.toString());
    }    
    
    @Test
    public void testUpdate() throws Exception {
        User user = UserMapper.getOne(3l);
        System.out.println(user.toString());
        user.setNickName("wxy");
        UserMapper.update(user);
        Assert.assertTrue(("wxy".equals(UserMapper.getOne(3l).getNickName())));
    }
}