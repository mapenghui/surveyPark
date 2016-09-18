package com.atguigu.surveypark.service;

import com.atguigu.surveypark.model.User;

public interface UserService extends BaseService<User> {

	public boolean isRegisted(String email);

	public User validateLoginInfo(String email, String md5);
	
}
