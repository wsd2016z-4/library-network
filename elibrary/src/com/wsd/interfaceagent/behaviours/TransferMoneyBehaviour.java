package com.wsd.interfaceagent.behaviours;

import java.math.BigDecimal;

import com.wsd.interfaceagent.DAO.UserAccountDAO;
import com.wsd.interfaceagent.DAO.UserDAO;
import com.wsd.interfaceagent.model.UserData;
import com.wsd.interfaceagent.model.UsersAccountData;

import jade.core.behaviours.Behaviour;

public class TransferMoneyBehaviour extends Behaviour {

	/**
	 * 
	 */
	private static final long serialVersionUID = -76751453014136098L;
	
	private UserDAO userDAO;
	private UserAccountDAO userAccountDAO;
	
	private String userLogin;
	private BigDecimal moneyAmount;

	public TransferMoneyBehaviour(String userLogin, BigDecimal moneyAmount) {
		super();
		this.userLogin = userLogin;
		this.moneyAmount = moneyAmount;
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
		double balance = usersAccountData.getBalance();
		balance += moneyAmount.doubleValue();
		usersAccountData.setBalance(balance);
		userAccountDAO.update(usersAccountData);
		userAccountDAO.closeCurrentSessionwithTransaction();
		System.out.println("Konto u¿ytkownika " + user.getUserLogin() + "zosta³o zasilone kwot¹ " + moneyAmount + " z³otych.");
	}

	@Override
	public boolean done() {
		return true;
	}

}
