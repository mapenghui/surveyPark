package com.atguigu.surveypark.model;

import java.util.HashSet;
import java.util.Set;

/*
 * ҳ����
 */

public class Page {
	private Integer id;
	private String title = "δ����";
	private String description;
	
	//��page��survey�Ķ��һ������ϵ
	private Survey survey;

	//��page��survey��һ�Զ������ϵ
	private Set<Question> questions = new HashSet<Question>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Survey getSurvey() {
		return survey;
	}

	public void setSurvey(Survey survey) {
		this.survey = survey;
	}

	public Set<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}
}