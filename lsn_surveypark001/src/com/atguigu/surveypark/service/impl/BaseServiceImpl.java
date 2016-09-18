package com.atguigu.surveypark.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.atguigu.surveypark.dao.BaseDao;
import com.atguigu.surveypark.service.BaseService;

/*
 * 抽象的BaseService,专门用于继承
 */
public abstract class BaseServiceImpl<T> implements BaseService<T> {
	
	private BaseDao<T> dao;

	@Resource
	public void setDao(BaseDao<T> dao) {
		this.dao = dao;
	}

	@Override
	public void saveEntity(T t) {
		dao.saveEntity(t);
	}

	@Override
	public void updateEntity(T t) {
		dao.updateEntity(t);
	}

	@Override
	public void saveOrUpdateEntity(T t) {
		dao.saveOrUpdateEntity(t);
	}

	@Override
	public void deleteEntity(T t) {
		dao.deleteEntity(t);
	}

	@Override
	public void batchEntityByHQL(String hql, Object... objects) {
		dao.batchEntityByHQL(hql, objects);
	}

	@Override
	public T loadEntity(Integer id) {
		return dao.loadEntity(id);
	}

	@Override
	public T getEntity(Integer id) {
		return dao.getEntity(id);
	}

	@Override
	public List<T> findEntityByHQL(String hql, Object... objects) {
		return dao.findEntityByHQL(hql, objects);
	}

}
