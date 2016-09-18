/**
 * ��������Դ
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
import com.atguigu.surveypark.service.SurveyService;
import com.atguigu.surveypark.service.UserService;
/*
 * ����SurveyService
 */
public class TestSurveyService {
	private static SurveyService ss;
	
	@BeforeClass
	public static void iniUserService() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		ss = (SurveyService)ac.getBean("surveyService");
	}
	/*
	 * �����û�
	 */
	@Test
	public void newSurvey() throws SQLException {
		User u = new User();
		u.setId(6);
		ss.newSurvey(u);
	} 
}