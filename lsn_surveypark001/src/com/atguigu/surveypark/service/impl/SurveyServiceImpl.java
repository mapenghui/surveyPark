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
 * SurveyService实现
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
	 * 查询调查集合
	 */
	public List<Survey> findMySurveys(User user) {
		String hql = "from Survey s where s.user.id = ?" ;
		return surveyDao.findEntityByHQL(hql,user.getId());
	}
	
	/**
	 * 新建调查
	 */
	public Survey newSurvey(User user){
		Survey s = new Survey();
		Page p = new Page();
		
		//设置关联关系
		s.setUser(user);
		p.setSurvey(s);
		s.getPages().add(p);
		
		surveyDao.saveEntity(s);
		pageDao.saveEntity(p);
		return s ;
	}
	
	/**
	 * 按照id查询Survey
	 */
	public Survey getSurvey(Integer sid){
		return surveyDao.getEntity(sid);
	}
	
	/**
	 * 按照id查询Survey,同时携带所有的孩子
	 */
	public Survey getSurveyWithChildren(Integer sid){
		Survey s = this.getSurvey(sid);
		//强行初始化pages和questions集合
		for(Page p : s.getPages()){
			p.getQuestions().size();
		}
		return s; 
	}
	
	/**
	 * 更新调查
	 */
	public void updateSurvey(Survey model){
		surveyDao.updateEntity(model);
	}
	
	/**
	 * 保存/更新页面
	 */
	public void saveOrUpdatePage(Page model){
		pageDao.saveOrUpdateEntity(model);
	}
	
	/**
	 * 按照id查询页面
	 */
	public Page getPage(Integer pid){
		return pageDao.getEntity(pid);
	}

	/**
	 * 保存/更新问题
	 */
	public void saveOrUpdateQuestion(Question model) {
		questionDao.saveOrUpdateEntity(model);
	}

	/*
	 * 删除问题 
	 */
	public void deleteQuestion(Integer qid) {
		//1.删除answer
		String hql = "delete from Answer a where a.questionId = ?";
		answerDao.batchEntityByHQL(hql, qid);
		
		//2.删除question
		hql = "delete from Question q where q.id = ?";
		questionDao.batchEntityByHQL(hql, qid);
	}

	@Override
	public void deletePage(Integer pid) {
		//1.删除answer
		String hql = "delete from Answer a where a.questionId in (select q.id from Question q where q.page.id = ?)";
		answerDao.batchEntityByHQL(hql, pid);
		
		//2.删除question
		hql = "delete from Question q where q.page.id = ?";
		questionDao.batchEntityByHQL(hql, pid);
		
		//3.删除page
		hql = "delete from Page p where p.id = ?";
		pageDao.batchEntityByHQL(hql, pid);
 	}

	/*
	 * 删除调查
	 */
	public void deleteSurvey(Integer sid) {
		//1.删除answer
		String hql = "delete from Answer a where a.surveyId = ?";
		answerDao.batchEntityByHQL(hql, sid);
		
		//2.删除question
		//hibernate 在写操作中，不允许两级以上的链接
		//hql = "delete from Question q where q.page.survey.id = ?";
		hql = "delete from Question q where q.page.id in (select p.id from Page p where p.survey.id = ?)";
		questionDao.batchEntityByHQL(hql, sid);
		
		//3.删除page
		hql = "delete from Page p where p.survey.id = ?";
		pageDao.batchEntityByHQL(hql, sid);
		
		//4.删除survey
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
