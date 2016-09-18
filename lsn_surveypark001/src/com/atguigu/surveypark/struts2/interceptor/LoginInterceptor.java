package com.atguigu.surveypark.struts2.interceptor;

import com.atguigu.surveypark.model.User;
import com.atguigu.surveypark.struts2.UserAware;
import com.atguigu.surveypark.struts2.action.BaseAction;
import com.atguigu.surveypark.struts2.action.LoginAction;
import com.atguigu.surveypark.struts2.action.RegAction;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

/*
 * 登陆拦截器
 */
public class LoginInterceptor implements Interceptor {

	private static final long serialVersionUID = -3220717826640111752L;

	public void destroy() {
	}

	public void init() {
	}

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		BaseAction action = (BaseAction) arg0.getAction();
		if(action instanceof LoginAction || action instanceof RegAction) {
			return arg0.invoke();
		}
		else {
			User user = (User) arg0.getInvocationContext().getSession().get("user");
			if(user == null) {
				//去登陆
				return "login";
			}
			else {
				//放行
				if(action instanceof UserAware) {
					//注入User给Session
					((UserAware)action).setUser(user);
				}
				return arg0.invoke();
			}
		}
	}

}
