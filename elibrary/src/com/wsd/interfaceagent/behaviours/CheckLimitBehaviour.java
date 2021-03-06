package com.wsd.interfaceagent.behaviours;

import com.wsd.interfaceagent.DAO.UserDAO;
import com.wsd.interfaceagent.DAO.UsersUptakesDAO;
import com.wsd.interfaceagent.model.UserData;

import jade.core.behaviours.Behaviour;

public class CheckLimitBehaviour extends Behaviour{

	private static final long serialVersionUID = 4623851040174719134L;
	// TODO moze lepiej w bazie to trzymic albo w jakims pliku konfiguracyjnym
	private static final int BOOK_RENT_LIMIT = 5;
	private String userLogin;
	private UsersUptakesDAO usersUptakesDAO;
	private UserDAO userDAO;
	
	public CheckLimitBehaviour(String userLogin) {
		super();
		this.userLogin = userLogin;
		usersUptakesDAO = new UsersUptakesDAO();
		userDAO = new UserDAO();
	}
	
	
	@Override
	public void action() {
		userDAO.openCurrentSession();
		UserData user = userDAO.findByLogin(userLogin);
		userDAO.closeCurrentSession();
		usersUptakesDAO.openCurrentSession();
		if (usersUptakesDAO.getNumberOfCurrentUptakes(user) < BOOK_RENT_LIMIT)
			System.out.println("Użytkownik nie osiągnął limitu wypożyczeń.");
		else
			System.out.println("Użytkownik osiągnął limit wypożyczeń.");
		usersUptakesDAO.closeCurrentSession();
		
	}


	@Override
	public boolean done() {
		return true;
	}

}
