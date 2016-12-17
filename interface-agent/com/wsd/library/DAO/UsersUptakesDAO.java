package com.wsd.library.DAO;

import java.util.List;

import com.wsd.library.model.UserData;
import com.wsd.library.model.UsersUptakesData;

public class UsersUptakesDAO extends DAOGeneric implements DAOInterface<UsersUptakesData, String>{

	public UsersUptakesDAO() {
	}
	
	public void persist(UsersUptakesData entity) {
		getCurrentSession().save(entity);
	}

	public void update(UsersUptakesData entity) {
		getCurrentSession().update(entity);
	}

	public UsersUptakesData findById(String id) {
		return (UsersUptakesData) getCurrentSession().get(UsersUptakesData.class, id);
	}

	public void delete(UsersUptakesData entity) {
		getCurrentSession().delete(entity);
	}

	@SuppressWarnings("unchecked")
	public List<UsersUptakesData> findAll() {
		return (List<UsersUptakesData>) getCurrentSession().createQuery("from UsersUptakesData").getResultList();
	}

	public void deleteAll() {
		List<UsersUptakesData> entityList = findAll();
		for (UsersUptakesData entity : entityList) {
			delete(entity);
		}
	}
	
	public int getNumberOfCurrentUptakes(UserData user) {
		@SuppressWarnings("unchecked")
		List<UsersUptakesData> result = (List<UsersUptakesData>) getCurrentSession().
										createQuery("from UsersUptakesData UU where UU.userData = :user AND UU.endDate is null").
											setParameter("user", user).getResultList();
		return result.size();
	}

}
