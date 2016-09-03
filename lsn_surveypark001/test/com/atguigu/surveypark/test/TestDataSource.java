/**
 * ≤‚ ‘ ˝æ›‘¥
 */
/**
 * @author Administrator
 *
 */
package com.atguigu.surveypark.test;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sun.java.swing.plaf.windows.WindowsInternalFrameTitlePane.ScalableIconUIResource;

public class TestDataSource {
	
	@Test
	public void getConnection() throws SQLException {
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans0.xml");
		DataSource ds = (DataSource)ac.getBean("dataSource");
		System.out.println(ds.getConnection());
	} 
}