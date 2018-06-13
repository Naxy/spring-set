package com.wxy.spring_boot.mail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wxy.spring_boot.email.MailService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceTests {
	@Autowired
	private MailService mailService;
	@Test
	public void setSimpleMail() throws Exception{
		mailService.sendSimpleMail("82910350@qq.com", "Test mail", "This is a test email!");
	}
}
