package com.atguigu.surveypark.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import com.atguigu.surveypark.dao.BaseDao;
import com.atguigu.surveypark.model.User;
import com.atguigu.surveypark.service.UserService;
import com.atguigu.surveypark.util.ValidateUtil;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements
		UserService {

	@Override
	@Resource(name="userDao")
	public void setDao(BaseDao<User> dao) {
		// TODO Auto-generated method stub
		super.setDao(dao);
	}

	/*
	 * 判断email是否被占用
	 */
	public boolean isRegisted(String email) {
		String hql = "from User u where u.email = ?";
		List<User> list = this.findEntityByHQL(hql, email);
		return ValidateUtil.isValid(list);
	}

	@Override
	public User validateLoginInfo(String email, String md5) {
		// TODO Auto-generated method stub
		String hql = "from User u where u.email = ? and u.password = ?";
		List<User> list = this.findEntityByHQL(hql, email, md5);
		return ValidateUtil.isValid(list) ? list.get(0):null;
	}
	
}
