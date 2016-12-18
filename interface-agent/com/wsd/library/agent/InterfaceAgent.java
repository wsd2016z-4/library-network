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

public class InterfaceAgent extends Agent{
	private static final long serialVersionUID = 8687660691708013194L;
	private List<AID> warehouses;

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
		} );
		
	}
	
	protected void takeDown() {
	}
	
	public List<AID> getWarehouses() {
		return warehouses;
	}

	public void setWarehouses(List<AID> warehouses) {
		this.warehouses = warehouses;
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

}
