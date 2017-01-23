package com.wsd.warehouseagent.agent;

import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import com.wsd.warehouseagent.behaviour.AwaitClientServer;
import com.wsd.warehouseagent.db.SessionFactoryProvider;

/** Glowna klasa programu, reprezentujaca agenta magazunu */
public class WarehouseAgent extends Agent
{

    @Override
    protected void setup()
    {
        addBehaviour(new AwaitClientServer());

        DFAgentDescription dfd = new DFAgentDescription();
        dfd.setName(getAID());
        ServiceDescription sd = new ServiceDescription();
        sd.setType("warehouse-agent");
        sd.setName(getLocalName());
        dfd.addServices(sd);

        try
        {
            DFService.register(this, dfd);
        }
        catch (FIPAException fe)
        {
            fe.printStackTrace();
        }

        System.out.println(getAID().getName() + " setup.");
    }

    @Override
    public void takeDown()
    {
        disconnectDb();
        System.out.println(getAID().getName() + " take down.");
    }

    /** Zakonczenie polaczenia z baza danych podczas konczenia pracy agenta */
    public void disconnectDb()
    {
        SessionFactoryProvider.shutdown();
        System.out.println("Closed connection to the database.");
    }
}