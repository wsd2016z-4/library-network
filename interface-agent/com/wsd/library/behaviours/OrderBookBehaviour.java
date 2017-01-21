package com.wsd.library.behaviours;

import com.wsd.library.agent.InterfaceAgent;
import com.wsd.library.message.ContentParser;
import com.wsd.library.message.MessageCreator;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class OrderBookBehaviour extends Behaviour {

	private static final long serialVersionUID = 5660656677933328483L;
	private MessageTemplate messageTemplate;
	private int step = 0;
	private int booksId;
	
	
	public OrderBookBehaviour(int booksId) {
		super();
		this.booksId = booksId;
	}

	@Override
	public void action() {
		switch (step) {
		case 0:
			// Wys³anie ACLMessage do WarehouseAgent z pytaniem o ksiazke o danym tytule
			MessageCreator messageCreator = new MessageCreator();
			ACLMessage message = messageCreator.orderBookMessage(booksId, ((InterfaceAgent) myAgent).getConnectedWarehouse());
			message.setSender(myAgent.getAID());
			message.setConversationId(BehaviourTypes.ORDER_BOOK);
			message.setReplyWith("request" + System.currentTimeMillis());
			myAgent.send(message);
			step = 1;
			messageTemplate = MessageTemplate.and(MessageTemplate.MatchConversationId(BehaviourTypes.ORDER_BOOK),
					MessageTemplate.MatchInReplyTo(message.getReplyWith()));
			break;
		case 1:
			// Odebranie odpowiedzi od agenta
			ACLMessage reply = myAgent.receive(messageTemplate);
			if (reply != null) {
				if (reply.getPerformative() == ACLMessage.CONFIRM) {
					ContentParser contentParser = new ContentParser(reply.getContent());
					int booksId = contentParser.getBooksId();
					System.out.println("Ksi¹¿ka o id: " + booksId + " zosta³a zamówiona.");
				} else if (reply.getPerformative() == ACLMessage.DISCONFIRM) {
					System.out.println("Nie uda³o siê zamówiæ ksi¹¿ki.");
				}
				step = 2;
			}			
		}
	}

	@Override
	public boolean done() {
		if (step == 2)
			return true;
		return false;
	}

}
