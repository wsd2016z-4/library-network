package com.wsd.library.behaviours;

import java.math.BigDecimal;

import jade.core.behaviours.Behaviour;

public class PayBehaviour extends Behaviour {

	private static final long serialVersionUID = 2585607017385742350L;
	
	private String userLogin;
	private BigDecimal price;
	
	public PayBehaviour(String userLogin, BigDecimal price) {
		super();
		this.userLogin = userLogin;
		this.price = price;
	}

	@Override
	public void action() {
		// TODO Tutaj trzeba pobraæ z bazy stan konta klienta, jak mniejszy ni¿ to co do zap³acenia to zwróciæ komunikat
		// a jak nie to zrealizowac platnosc (odjac w bazie mu tyle hajsu) i zwrocic ze sie powiodlo 
		
	}

	@Override
	public boolean done() {
		// TODO Auto-generated method stub
		return false;
	}

}
