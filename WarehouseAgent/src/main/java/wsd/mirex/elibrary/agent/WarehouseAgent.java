package wsd.mirex.elibrary.agent;

import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wsd.mirex.elibrary.behaviour.AwaitClientServer;
import wsd.mirex.elibrary.db.SessionFactoryProvider;

/** Glowna klasa programu, reprezentujaca agenta magazunu */
public class WarehouseAgent extends Agent
{
    private static final Logger logger = LoggerFactory.getLogger(WarehouseAgent.class);

    @Override
    protected void setup()
    {
        addBehaviour(new AwaitClientServer());

        DFAgentDescription dfd = new DFAgentDescription();
        dfd.setName(getAID());
        ServiceDescription sd = new ServiceDescription();
        sd.setType("warehouse-agent");
        sd.setName("warehouse-agent");
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