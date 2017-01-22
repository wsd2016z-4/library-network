package com.wsd.library.DAO;

import java.util.List;

import com.wsd.library.model.BooksData;

public class BooksDAO extends DAOGeneric implements DAOInterface<BooksData, String> {
	public BooksDAO() {
	}
	
	public void persist(BooksData entity) {
		getCurrentSession().save(entity);
	}

	public void update(BooksData entity) {
		getCurrentSession().update(entity);
	}

	public BooksData findById(int id) {
		return (BooksData) getCurrentSession().get(BooksData.class, id);
	}

	public void delete(BooksData entity) {
		getCurrentSession().delete(entity);
	}

	@SuppressWarnings("unchecked")
	public List<BooksData> findAll() {
		return (List<BooksData>) getCurrentSession().createQuery("from BooksData").getResultList();
	}

	public void deleteAll() {
		List<BooksData> entityList = findAll();
		for (BooksData entity : entityList) {
			delete(entity);
		}
	}

	@Override
	public BooksData findById(String id) {
		return (BooksData) getCurrentSession().get(BooksData.class, id);
	}
}
