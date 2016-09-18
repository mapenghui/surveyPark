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
	
	//ע��SurveyService
	@Resource
	private SurveyService surveyService ;

	//���鼯��
	private List<Survey> mySurveys ;

	//����user����
	private User user;

	//����sid����
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
	 * ��ѯ�ҵĵ����б�
	 */
	public String mySurveys(){
		this.mySurveys = surveyService.findMySurveys(user);
		return "mySurveyListPage" ;
	}
	
	/**
	 * �½�����
	 */
	public String newSurvey(){
		this.model = surveyService.newSurvey(user);
		return "designSurveyPage" ;
	}
	
	/**
	 * ��Ƶ���
	 */
	public String designSurvey(){
		this.model = surveyService.getSurveyWithChildren(sid);
		return "designSurveyPage" ;
	}

	/**
	 * �÷���ֻ��designSurvey֮ǰ����
	 */
//	public void prepareDesignSurvey(){
//		this.model = surveyService.getSurveyWithChildren(sid);
//	}

	/*
	 * �༭����
	 */
	public String editSurvey() {
		this.model = surveyService.getSurvey(sid);
		return "editSurveyPage";
	}
	
	/**
	 * ���µ���
	 */
	public String updateSurvey(){
		this.sid = model.getId();
		//���ֹ�����ϵ
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
	
	//ע��User����
	public void setUser(User user) {
		this.user = user ;
	}
	
}
