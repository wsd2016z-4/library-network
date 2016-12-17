package com.wsd.library.behaviours;

import com.wsd.library.DAO.UserDAO;
import com.wsd.library.DAO.UsersUptakesDAO;
import com.wsd.library.model.UserData;

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
		// TODO Zapytanie do bazy ile wypo¿yczonych, zwracamy czy >= maks 
		userDAO.openCurrentSession();
		UserData user = userDAO.findByLogin(userLogin);
		userDAO.closeCurrentSession();
		usersUptakesDAO.openCurrentSession();
		if (usersUptakesDAO.getNumberOfCurrentUptakes(user) < BOOK_RENT_LIMIT)
			System.out.println("U¿ytkownik nie osi¹gn¹³ limitu wypo¿yczeñ.");
		else
			System.out.println("U¿ytkownik osi¹gn¹³ limit wypo¿yczeñ.");
		usersUptakesDAO.closeCurrentSession();
		
	}


	@Override
	public boolean done() {
		return true;
	}

}
