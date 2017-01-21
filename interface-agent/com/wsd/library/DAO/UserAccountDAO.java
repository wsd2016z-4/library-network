package com.wsd.library.DAO;

import java.util.List;

import com.wsd.library.model.UserData;
import com.wsd.library.model.UsersAccountData;

public class UserAccountDAO extends DAOGeneric implements DAOInterface<UsersAccountData, String> {

	public UserAccountDAO() {
	}
	
	public void persist(UsersAccountData entity) {
		getCurrentSession().save(entity);
	}

	public void update(UsersAccountData entity) {
		getCurrentSession().update(entity);
	}

	public UsersAccountData findById(String id) {
		return (UsersAccountData) getCurrentSession().get(UsersAccountData.class, id);
	}

	public void delete(UsersAccountData entity) {
		getCurrentSession().delete(entity);
	}

	@SuppressWarnings("unchecked")
	public List<UsersAccountData> findAll() {
		return (List<UsersAccountData>) getCurrentSession().createQuery("from UsersAccountData").getResultList();
	}

	public void deleteAll() {
		List<UsersAccountData> entityList = findAll();
		for (UsersAccountData entity : entityList) {
			delete(entity);
		}
	}
	
	@SuppressWarnings("unchecked")
	public UsersAccountData getUserAccount(UserData userData) {
		List<UsersAccountData> result = (List<UsersAccountData>) getCurrentSession().
				createQuery("from UsersAccountData UA where UA.userData = :user").
					setParameter("user", userData).getResultList();
		return result.get(0);
	}

}
