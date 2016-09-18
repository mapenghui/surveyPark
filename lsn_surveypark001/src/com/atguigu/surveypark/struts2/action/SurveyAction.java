package com.atguigu.surveypark.struts2.action;

import java.util.List;

import javax.annotation.Resource;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.atguigu.surveypark.dao.impl.SurveyDaoImpl;
import com.atguigu.surveypark.model.Survey;
import com.atguigu.surveypark.model.User;
import com.atguigu.surveypark.service.SurveyService;
import com.atguigu.surveypark.struts2.UserAware;

/**
 * SurveyAction
 */
@Controller
@Scope("prototype")
public class SurveyAction extends BaseAction<Survey> implements UserAware{

	private static final long serialVersionUID = 2438909978838628762L;
	
	//注入SurveyService
	@Resource
	private SurveyService surveyService ;

	//调查集合
	private List<Survey> mySurveys ;

	//接受user对象
	private User user;

	//接受sid参数
	private Integer sid ;
	
	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public List<Survey> getMySurveys() {
		return mySurveys;
	}

	public void setMySurveys(List<Survey> mySurveys) {
		this.mySurveys = mySurveys;
	}

	/**
	 * 查询我的调查列表
	 */
	public String mySurveys(){
		this.mySurveys = surveyService.findMySurveys(user);
		return "mySurveyListPage" ;
	}
	
	/**
	 * 新建调查
	 */
	public String newSurvey(){
		this.model = surveyService.newSurvey(user);
		return "designSurveyPage" ;
	}
	
	/**
	 * 设计调查
	 */
	public String designSurvey(){
		this.model = surveyService.getSurveyWithChildren(sid);
		return "designSurveyPage" ;
	}

	/**
	 * 该方法只在designSurvey之前调用
	 */
//	public void prepareDesignSurvey(){
//		this.model = surveyService.getSurveyWithChildren(sid);
//	}

	/*
	 * 编辑调查
	 */
	public String editSurvey() {
		this.model = surveyService.getSurvey(sid);
		return "editSurveyPage";
	}
	
	/**
	 * 更新调查
	 */
	public String updateSurvey(){
		this.sid = model.getId();
		//保持关联关系
		model.setUser(user);
		surveyService.updateSurvey(model);
		return "designSurveyAction" ;
	}
	

	/**
	 * delete survey
	 */
	public String deleteSurvey(){
		surveyService.deleteSurvey(sid);
		return "findMySurveysAction" ;
	}
	
	//注入User对象
	public void setUser(User user) {
		this.user = user ;
	}
	
}
