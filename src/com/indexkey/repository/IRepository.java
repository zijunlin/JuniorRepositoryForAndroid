package com.indexkey.repository;

import java.util.List;



public interface IRepository<T>   {
	T getEntityByKey(String root);
	List<T> getAllEntity();
	void SaveEntity(T entity);
	void DeleteEntity(T entity);
	void EditEntity(T entity);
}
