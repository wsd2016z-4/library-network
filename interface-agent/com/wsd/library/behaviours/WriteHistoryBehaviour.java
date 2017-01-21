package com.wsd.library.behaviours;

import java.util.Date;

import com.wsd.library.DAO.UsersUptakesDAO;
import com.wsd.library.model.BooksData;
import com.wsd.library.model.UserData;
import com.wsd.library.model.UsersUptakesData;

import jade.core.behaviours.Behaviour;

public class WriteHistoryBehaviour extends Behaviour {

	public WriteHistoryBehaviour(int userId, int bookId) {
		super();
		this.userId = userId;
		this.bookId = bookId;
		usersUptakesDAO = new UsersUptakesDAO();
	}

	private static final long serialVersionUID = 4009927678506581004L;
	private int userId;
	private int bookId;
	private UsersUptakesDAO usersUptakesDAO;
	
	
	@Override
	public void action() {
		UsersUptakesData usersUptakesData = new UsersUptakesData();
		BooksData booksData = new BooksData();
		booksData.setId(bookId);
		UserData userData = new UserData();
		userData.setId(userId);
		usersUptakesData.setBooksData(booksData);
		usersUptakesData.setUserData(userData);
		usersUptakesData.setStartDate(new Date());
		usersUptakesDAO.openCurrentSessionwithTransaction();
		usersUptakesDAO.persist(usersUptakesData);;
		usersUptakesDAO.closeCurrentSessionwithTransaction();
	}

	@Override
	public boolean done() {
		return true;
	}

}
