package com.wsd.library.agent;

import java.util.ArrayList;
import java.util.List;

import com.wsd.library.behaviours.AwaitClientServer;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;

public class InterfaceAgent extends Agent{
	private static final long serialVersionUID = 8687660691708013194L;
	private List<AID> warehouses;
	private AID connectedWarehouse;
	private ACLMessage currentMessage;
	
	public ACLMessage getCurrentMessage() {
		return currentMessage;
	}

	public void setCurrentMessage(ACLMessage currentMessage) {
		this.currentMessage = currentMessage;
	}

	protected void setup() {
		// Cykliczny behaviour w ktorym sa parsowane message i uruchamiane inne
		addBehaviour(new AwaitClientServer());
		// Co 60 sekund aktualizowana lista aktywnych WarehouseAgent
		addBehaviour(new TickerBehaviour(this, 60000) {
			private static final long serialVersionUID = 1075237925693108874L;

			@Override
			protected void onTick() {
				updateWarehouseAgentsList();
			}
		});
		findConnectedWarehouse();
	}
	
	protected void takeDown() {
	}
	
	public List<AID> getWarehouses() {
		return warehouses;
	}

	public void setWarehouses(List<AID> warehouses) {
		this.warehouses = warehouses;
	}
	
	public AID getConnectedWarehouse() {
		return connectedWarehouse;
	}

	private void updateWarehouseAgentsList() {
		DFAgentDescription template = new DFAgentDescription();
		ServiceDescription serviceDescription = new ServiceDescription();
		serviceDescription.setType("warehouse-agent");
		template.addServices(serviceDescription);
		try {
			DFAgentDescription[] result = DFService.search(this, template);
			warehouses = new ArrayList<AID>();
			for (int i = 0; i < result.length; ++i) {
				warehouses.add(result[i].getName());
			}
		}
		catch (FIPAException fe) {
			fe.printStackTrace();
		}
	}
	
	private void findConnectedWarehouse() {
		String[] elems = this.getName().split("@");
		String name = elems[0];
		elems = name.split("-");
		if (elems.length > 2) {
			int number = Integer.valueOf(elems[2]);
			DFAgentDescription template = new DFAgentDescription();
			ServiceDescription serviceDescription = new ServiceDescription();
			serviceDescription.setType("warehouse-agent");
			serviceDescription.setName("warehouse-agent-" + number);
			template.addServices(serviceDescription);
			try {
				DFAgentDescription[] result = DFService.search(this, template);
				if (result.length > 0)
					connectedWarehouse = result[0].getName();
			}
			catch (FIPAException fe) {
				fe.printStackTrace();
			}
		}
	}

}
