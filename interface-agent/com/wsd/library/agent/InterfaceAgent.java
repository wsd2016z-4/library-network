package com.wsd.library.agent;

import com.wsd.library.behaviours.AwaitClientServer;
import jade.core.Agent;

public class InterfaceAgent extends Agent{
	private static final long serialVersionUID = 8687660691708013194L;

	protected void setup() {
		System.out.println("Hello! Buyer-agent " + getAID().getName() + " is ready.");
		addBehaviour(new AwaitClientServer());
	}
	
	protected void takeDown() {
		System.out.println("Buyer-agent "+getAID().getName()+" terminating.");
	}

}
