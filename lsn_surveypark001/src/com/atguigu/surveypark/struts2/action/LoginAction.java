package com.atguigu.surveypark.struts2.action;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.atguigu.surveypark.model.User;
import com.atguigu.surveypark.service.UserService;
import com.atguigu.surveypark.util.DataUtil;

/*
 * ��½Action
 */
@Controller
@Scope("prototype")
public class LoginAction extends BaseAction<User> implements SessionAware{

	private static final long serialVersionUID = 1720208723721475688L;

	private Map<String, Object> sessionMap;
	
	@Resource
	private UserService userService;
	
	/*
	 * �����½ҳ��
	 */
	public String toLoginPage() {
		return "loginPage" ;
	}
	
	public String doLogin() {
		return SUCCESS;
	}
	
	/*
	 * У���½��Ϣ   validateDoLogin / validateDoLogin ����ֻ��doLogin����У��
	 */
	public void validateDoLogin() {
		//1.��֤��½��Ϣ
		User user = userService.validateLoginInfo(model.getEmail(), DataUtil.md5(model.getPassword()));
		if(user == null) {
			addActionError("email/password����");
		}
		else {
			sessionMap.put("user", user);
		}
	}

	//ע��session��map
	public void setSession(Map<String, Object> arg0) {
		this.sessionMap = arg0;
	}
	
}
