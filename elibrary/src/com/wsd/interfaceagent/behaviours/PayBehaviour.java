package com.wsd.interfaceagent.behaviours;

import java.math.BigDecimal;

import com.wsd.interfaceagent.DAO.UserAccountDAO;
import com.wsd.interfaceagent.DAO.UserDAO;
import com.wsd.interfaceagent.model.UserData;
import com.wsd.interfaceagent.model.UsersAccountData;

import jade.core.behaviours.Behaviour;

public class PayBehaviour extends Behaviour {

	private static final long serialVersionUID = 2585607017385742350L;
	
	private UserDAO userDAO;
	private UserAccountDAO userAccountDAO;
	private String userLogin;
	private BigDecimal price;
	
	public PayBehaviour(String userLogin, BigDecimal price) {
		super();
		this.userLogin = userLogin;
		this.price = price;
		userDAO = new UserDAO();
		userAccountDAO = new UserAccountDAO();
	}

	@Override
	public void action() {
		userDAO.openCurrentSession();
		UserData user = userDAO.findByLogin(userLogin);
		userDAO.closeCurrentSession();
		userAccountDAO.openCurrentSessionwithTransaction();
		UsersAccountData usersAccountData = userAccountDAO.getUserAccount(user);
		if (usersAccountData.getBalance() >= price.doubleValue()) {
			double balance = usersAccountData.getBalance() - price.doubleValue();
			usersAccountData.setBalance(balance);
			userAccountDAO.update(usersAccountData);
			System.out.println("Transkacja przebieg³a pomyœlnie.");
		} else 
			System.out.println("Za ma³o œrodków na koncie.");
		userAccountDAO.closeCurrentSessionwithTransaction();
	}

	@Override
	public boolean done() {
		return true;
	}

}
