package com.wsd.library.behaviours;

import jade.core.behaviours.Behaviour;

public class WriteHistoryBehaviour extends Behaviour {

	private static final long serialVersionUID = 4009927678506581004L;

	@Override
	public void action() {
		// TODO po prostu dodanie wpisu w bazie ze ksiazka zostala wypozyczona (zmiana statusu ksiazki i dodanie/nadpisanie
		// rekordu w tabelce z historia wypozyczen, historia transakcji moze tez (wplat i oplat)
		
	}

	@Override
	public boolean done() {
		// TODO Auto-generated method stub
		return false;
	}

}
