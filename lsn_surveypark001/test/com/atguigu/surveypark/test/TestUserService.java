/**
 * 测试数据源
 */
/**
 * @author Administrator
 *
 */
package com.atguigu.surveypark.test;

import java.sql.SQLException;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atguigu.surveypark.model.User;
import com.atguigu.surveypark.service.UserService;
/*
 * 测试UserService
 */
public class TestUserService {
	private static UserService us;
	
	@BeforeClass
	public static void iniUserService() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		us = (UserService)ac.getBean("userService");
	}
	/*
	 * 插入用户
	 */
	@Test
	public void insertUser() throws SQLException {
		User u = new User();
		u.setEmail("524764246@qq.com");
		u.setPassword("12345");
		u.setNickName("stone");
		us.saveEntity(u);
	} 
}