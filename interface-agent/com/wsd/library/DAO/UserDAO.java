package com.wsd.library.DAO;

import java.util.List;

import com.wsd.library.model.UserData;

public class UserDAO extends DAOGeneric implements DAOInterface<UserData, String> {

	public UserDAO() {
	}
	
	public void persist(UserData entity) {
		getCurrentSession().save(entity);
	}

	public void update(UserData entity) {
		getCurrentSession().update(entity);
	}

	public UserData findById(String id) {
		return (UserData) getCurrentSession().get(UserData.class, id);
	}

	public void delete(UserData entity) {
		getCurrentSession().delete(entity);
	}

	@SuppressWarnings("unchecked")
	public List<UserData> findAll() {
		return (List<UserData>) getCurrentSession().createQuery("from UserData").getResultList();
	}

	public void deleteAll() {
		List<UserData> entityList = findAll();
		for (UserData entity : entityList) {
			delete(entity);
		}
	}
	
	public UserData findByLogin(String login) {
		@SuppressWarnings("unchecked")
		List<UserData> result = (List<UserData>) getCurrentSession().createQuery("FROM UserData U where U.userLogin=:login").
			setParameter("login", login).getResultList();
		if (result.isEmpty())
			return null;
		else
			return result.get(0); 
	}
	
}