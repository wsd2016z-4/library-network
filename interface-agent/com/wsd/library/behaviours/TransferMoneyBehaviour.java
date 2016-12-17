package com.wsd.library.behaviours;

import java.math.BigDecimal;

import jade.core.behaviours.Behaviour;

public class TransferMoneyBehaviour extends Behaviour {

	/**
	 * 
	 */
	private static final long serialVersionUID = -76751453014136098L;
	
	private String userLogin;
	private BigDecimal moneyAmount;

	public TransferMoneyBehaviour(String userLogin, BigDecimal moneyAmount) {
		super();
		this.userLogin = userLogin;
		this.moneyAmount = moneyAmount;
	}

	@Override
	public void action() {
		// TODO Tutaj siê trzeba zastanowiæ, byæ mo¿e po prostu bezwarunkowe dodanie kasy do konta usera 
		// (przy zalozeniu ze jakis zewn. system obsluguje transakcje bankowa)
		
	}

	@Override
	public boolean done() {
		// TODO Auto-generated method stub
		return false;
	}

}
