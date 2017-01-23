package com.wsd.interfaceagent.behaviours;

import com.wsd.interfaceagent.DAO.UserDAO;
import com.wsd.interfaceagent.model.UserData;

import jade.core.behaviours.Behaviour;

public class CreateAccountBehaviour extends Behaviour{

	private static final long serialVersionUID = -3216848266999282784L;
	private UserData userData;
	private static UserDAO userDAO;


	public CreateAccountBehaviour(UserData userData) {
		super();
		this.userData = userData;
		userDAO = new UserDAO();
	}

	@Override
	public void action() {
		userDAO.openCurrentSession();
		UserData userFromDB = userDAO.findByLogin(userData.getUserLogin());
		userDAO.closeCurrentSession();
		if (userFromDB == null) {
			userDAO.openCurrentSessionwithTransaction();
			userDAO.persist(userData);
			userDAO.closeCurrentSessionwithTransaction();
			System.out.println("Utworzono nowego u¿ytkownika.");
		} else {
			System.out.println("U¿ytkownik o podanym loginie istnieje ju¿ w bazie danych.");
		}
		// TODO zwrocenie komunikatu do GUI
	}

	@Override
	public boolean done() {
		return true;
	}

}
