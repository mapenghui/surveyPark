package com.atguigu.surveypark.struts2.action;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.atguigu.surveypark.model.User;
import com.atguigu.surveypark.service.UserService;
import com.atguigu.surveypark.util.DataUtil;
import com.atguigu.surveypark.util.ValidateUtil;

/*
 * ע��Action
 */

@Controller
@Scope("prototype")
public class RegAction extends BaseAction<User> {

	private static final long serialVersionUID = 7351588309970506225L;
	
	private String confirmPassword;
	
	@Resource
	private UserService userService;
	
	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	/*
	 * ����ע�����
	 */
	@SkipValidation
	public String toRegPage() {
		return "regPage";
	}
	
	/*
	 * �����û�ע��
	 */
	public String doReg() {
		model.setPassword(DataUtil.md5(model.getPassword()));
		userService.saveEntity(model);
		return SUCCESS;
	}
	
	/*
	 * У��
	 */
	public void validate() {
		//1.�ǿ�
		String email = model.getEmail();
		if(!ValidateUtil.isValid(email)) {
			addFieldError("email", "email�Ǳ�����");
		}
		if(!ValidateUtil.isValid(model.getPassword())) {
			addFieldError("password", "password�Ǳ�����");
		}
		if(!ValidateUtil.isValid(model.getNickName())) {
			addFieldError("nickName", "nickName�Ǳ�����");
		}
		if(hasErrors()) {
			return;
		}
		
		//2.����һ����
		if(!model.getPassword().equals(confirmPassword)) {
			addFieldError("password", "���벻һ��");
			return;
		}
		
		//3.emailռ��
		if(userService.isRegisted(model.getEmail())) {
			addFieldError("email", "email�Ѿ�ռ��");
			return;
		}
	}
}
