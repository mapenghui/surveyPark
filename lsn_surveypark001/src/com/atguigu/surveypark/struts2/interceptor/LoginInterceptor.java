package com.atguigu.surveypark.struts2.interceptor;

import com.atguigu.surveypark.model.User;
import com.atguigu.surveypark.struts2.UserAware;
import com.atguigu.surveypark.struts2.action.BaseAction;
import com.atguigu.surveypark.struts2.action.LoginAction;
import com.atguigu.surveypark.struts2.action.RegAction;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

/*
 * ��½������
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
				//ȥ��½
				return "login";
			}
			else {
				//����
				if(action instanceof UserAware) {
					//ע��User��Session
					((UserAware)action).setUser(user);
				}
				return arg0.invoke();
			}
		}
	}

}
