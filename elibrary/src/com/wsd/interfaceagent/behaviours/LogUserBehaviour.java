package com.wsd.interfaceagent.behaviours;

import com.wsd.interfaceagent.DAO.UserDAO;
import com.wsd.interfaceagent.model.UserData;

import jade.core.behaviours.Behaviour;

public class LogUserBehaviour extends Behaviour {

	private UserDAO userDAO;
	
	public LogUserBehaviour(String userLogin, String userPassword) {
		super();
		this.userLogin = userLogin;
		this.userPassword = userPassword;
		userDAO = new UserDAO();
	}

	private static final long serialVersionUID = -4889249230935989409L;
	private String userLogin;
	private String userPassword;

	@Override
	public void action() {
		userDAO.openCurrentSession();
		UserData userFromDB = userDAO.findByLogin(userLogin);
		userDAO.closeCurrentSession();
		if (userFromDB == null)
			System.out.println("U¿ytkownik o podanym loginie nie istnieje w bazie danych.");
		else {
			if (userFromDB.getUserPass().equals(userPassword)) 
				System.out.println("Poprawnie zalogowano.");
			else
				System.out.println("Nie poprawne has³o.");
		}
		// TODO zwrocenie komunikatu do GUI
	}

	@Override
	public boolean done() {
		return true;
	}

}
