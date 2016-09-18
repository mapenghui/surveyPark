package com.atguigu.surveypark.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.aspectj.org.eclipse.jdt.internal.core.NameLookup.Answer;
import org.springframework.stereotype.Service;

import com.atguigu.surveypark.dao.BaseDao;
import com.atguigu.surveypark.model.Page;
import com.atguigu.surveypark.model.Question;
import com.atguigu.surveypark.model.Survey;
import com.atguigu.surveypark.model.User;
import com.atguigu.surveypark.service.SurveyService;

/**
 * SurveyServiceʵ��
 */
@Service("surveyService")
public class SurveyServiceImpl implements SurveyService {

	@Resource(name="surveyDao")
	private BaseDao<Survey> surveyDao ;
	
	@Resource(name="pageDao")
	private BaseDao<Page> pageDao ;
	
	@Resource(name="questionDao")
	private BaseDao<Question> questionDao ;
	
	@Resource(name="answerDao")
	private BaseDao<Answer> answerDao ;
	
	/**
	 * ��ѯ���鼯��
	 */
	public List<Survey> findMySurveys(User user) {
		String hql = "from Survey s where s.user.id = ?" ;
		return surveyDao.findEntityByHQL(hql,user.getId());
	}
	
	/**
	 * �½�����
	 */
	public Survey newSurvey(User user){
		Survey s = new Survey();
		Page p = new Page();
		
		//���ù�����ϵ
		s.setUser(user);
		p.setSurvey(s);
		s.getPages().add(p);
		
		surveyDao.saveEntity(s);
		pageDao.saveEntity(p);
		return s ;
	}
	
	/**
	 * ����id��ѯSurvey
	 */
	public Survey getSurvey(Integer sid){
		return surveyDao.getEntity(sid);
	}
	
	/**
	 * ����id��ѯSurvey,ͬʱЯ�����еĺ���
	 */
	public Survey getSurveyWithChildren(Integer sid){
		Survey s = this.getSurvey(sid);
		//ǿ�г�ʼ��pages��questions����
		for(Page p : s.getPages()){
			p.getQuestions().size();
		}
		return s; 
	}
	
	/**
	 * ���µ���
	 */
	public void updateSurvey(Survey model){
		surveyDao.updateEntity(model);
	}
	
	/**
	 * ����/����ҳ��
	 */
	public void saveOrUpdatePage(Page model){
		pageDao.saveOrUpdateEntity(model);
	}
	
	/**
	 * ����id��ѯҳ��
	 */
	public Page getPage(Integer pid){
		return pageDao.getEntity(pid);
	}

	/**
	 * ����/��������
	 */
	public void saveOrUpdateQuestion(Question model) {
		questionDao.saveOrUpdateEntity(model);
	}

	/*
	 * ɾ������ 
	 */
	public void deleteQuestion(Integer qid) {
		//1.ɾ��answer
		String hql = "delete from Answer a where a.questionId = ?";
		answerDao.batchEntityByHQL(hql, qid);
		
		//2.ɾ��question
		hql = "delete from Question q where q.id = ?";
		questionDao.batchEntityByHQL(hql, qid);
	}

	@Override
	public void deletePage(Integer pid) {
		//1.ɾ��answer
		String hql = "delete from Answer a where a.questionId in (select q.id from Question q where q.page.id = ?)";
		answerDao.batchEntityByHQL(hql, pid);
		
		//2.ɾ��question
		hql = "delete from Question q where q.page.id = ?";
		questionDao.batchEntityByHQL(hql, pid);
		
		//3.ɾ��page
		hql = "delete from Page p where p.id = ?";
		pageDao.batchEntityByHQL(hql, pid);
 	}

	/*
	 * ɾ������
	 */
	public void deleteSurvey(Integer sid) {
		//1.ɾ��answer
		String hql = "delete from Answer a where a.surveyId = ?";
		answerDao.batchEntityByHQL(hql, sid);
		
		//2.ɾ��question
		//hibernate ��д�����У��������������ϵ�����
		//hql = "delete from Question q where q.page.survey.id = ?";
		hql = "delete from Question q where q.page.id in (select p.id from Page p where p.survey.id = ?)";
		questionDao.batchEntityByHQL(hql, sid);
		
		//3.ɾ��page
		hql = "delete from Page p where p.survey.id = ?";
		pageDao.batchEntityByHQL(hql, sid);
		
		//4.ɾ��survey
		hql = "delete from Survey s where s.id = ?";
		pageDao.batchEntityByHQL(hql, sid);
	}

	/*
	 * 
	 */
	public Question getQuestion(Integer qid) {
		return questionDao.getEntity(qid);
	}
}
