package com.atguigu.surveypark.dao;

import java.util.List;

public interface BaseDao<T> {
	//Ð´²Ù×÷
	public void saveEntity(T t);
	public void updateEntity(T t);
	public void saveOrUpdateEntity(T t);
	public void deleteEntity(T t);
	public void batchEntityByHQL(String hql, Object...objects);
	
	//¶Á²Ù×÷
	public T loadEntity(Integer id);
	public T getEntity(Integer id);
	public List<T> findEntityByHQL(String hql, Object...objects);
}
