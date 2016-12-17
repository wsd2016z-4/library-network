package com.wsd.library.behaviours;

import jade.core.behaviours.Behaviour;

public class SearchBooksBehaviour extends Behaviour {

	private static final long serialVersionUID = -43590093686098595L;

	@Override
	public void action() {
		// TODO Utworzenie XML z search criteria na podstawie tego co dostaniemy z GUI, wyslanie ACLM do Warehouse'a,
		// czekanie na odpowiedz, jesli nie ma w tym do ktorego przypisany jest dany interface to rozsyla do reszty 
		// ogolnie trzeba sie zastanowic jak to ma dzialac, efektem ma byc zlokalizowanie ksiazki do wypozyczenia
		
	}

	@Override
	public boolean done() {
		// TODO Auto-generated method stub
		return false;
	}

}
