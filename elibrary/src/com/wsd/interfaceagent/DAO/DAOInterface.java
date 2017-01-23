package com.wsd.interfaceagent.DAO;

import java.io.Serializable;
import java.util.List;

public interface DAOInterface<T, Id extends Serializable> {
	
	public void persist(T entity);
	
	public void update(T entity);
	
	public T findById(Id id);
	
	public void delete(T entity);
	
	public List<T> findAll();
	
	public void deleteAll();
	
}
