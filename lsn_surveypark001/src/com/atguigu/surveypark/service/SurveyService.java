package com.atguigu.surveypark.service;

import java.util.List;

import com.atguigu.surveypark.model.Page;
import com.atguigu.surveypark.model.Question;
import com.atguigu.surveypark.model.Survey;
import com.atguigu.surveypark.model.User;

public interface SurveyService {

	/*
	 * 查询调查列表
	 */
	public List<Survey> findMySurveys(User user);
	
	public Survey newSurvey(User user);

	public Survey getSurvey(Integer sid);

	public Survey getSurveyWithChildren(Integer sid);

	public void updateSurvey(Survey model);

	public Page getPage(Integer pid);

	public void saveOrUpdatePage(Page model);

	public void saveOrUpdateQuestion(Question model);

	public void deleteQuestion(Integer qid);

	public void deletePage(Integer pid);

	public void deleteSurvey(Integer sid);

	public Question getQuestion(Integer qid);
}
