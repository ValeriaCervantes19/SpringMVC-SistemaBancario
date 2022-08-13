package com.sistemabancario.persistence;
import java.util.List;
import java.util.Map;

public interface GenericDao {
	//? representa cualquier hijo 
	public List<? extends Object> findAll();
	public Boolean update(Object entity);
	public Boolean create(Object entity);
	public Boolean delete(Object entity);
	public Object findById(Object id);
	public List<? extends Object> findByExample(Map<String,Object> conditions);
	public Object lastElement();
}