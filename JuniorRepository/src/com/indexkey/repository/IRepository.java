package com.indexkey.repository;

import java.util.HashMap;
import java.util.List;

import com.indexkey.repository.dbutility.ICursorDeserializer;



public interface IRepository<T>  extends ICursorDeserializer<T> {
	T getEntityByKey(String root);
	List<T> getAllEntity();
	void SaveEntity(T entity);
	void DeleteEntity(T entity);
	void EditEntity(T entity);
	void EditEntity(HashMap<String, Object> ColAndValue);
}
