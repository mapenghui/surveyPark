package com.atguigu.surveypark.struts2.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.atguigu.surveypark.model.Page;
import com.atguigu.surveypark.model.Question;
import com.atguigu.surveypark.service.SurveyService;

@Controller
@Scope("prototype")
public class QuestionAction extends BaseAction<Question> {

	private static final long serialVersionUID = -4877070727923971608L;

	private Integer sid;
	
	private Integer pid;
	
	private Integer qid;
	
	//ע��surveyService
	@Resource
	private SurveyService surveyService ;
	
	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}
	
	public Integer getQid() {
		return qid;
	}

	public void setQid(Integer qid) {
		this.qid = qid;
	}

	/*
	 * ����ѡ������ҳ��
	 */
	public String toSelectQuestionType() {
		return "selectQuestionTypePage";
	}
	
	/*
	 * �����������ҳ��
	 */
	public String toDesignQuestionPage() {
		return "" + model.getQuestionType();
	}
	
	/*
	 * �༭����
	 */
	public String editQuestion() {
		this.model = surveyService.getQuestion(qid);
		return "" + model.getQuestionType();
	}
	
	/*
	 * ����/����ҳ��
	 */
	public String saveOrUpdateQuestion() {
		Page p = new Page();
		p.setId(pid);
		//ά��������ϵ
		model.setPage(p);
		surveyService.saveOrUpdateQuestion(model);
		return "designSurveyAction";
	}
	
	/*
	 * ɾ������
	 */
	public String deleteQuestion() {
		surveyService.deleteQuestion(qid);
		return "designSurveyAction";
	}
}
