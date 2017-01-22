package com.wsd.library.behaviours;

import java.util.Date;

import com.wsd.library.DAO.UsersUptakesDAO;
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
	private String booksTitle;
	private UsersUptakesDAO userUptakesDAO;
	
	
	public OrderBookBehaviour(String booksTitle) {
		super();
		this.booksTitle = booksTitle;
		userUptakesDAO = new UsersUptakesDAO();
	}

	@Override
	public void action() {
		switch (step) {
		case 0:
			// Wys³anie ACLMessage do WarehouseAgent z pytaniem o mo¿liwoœæ oddania ksiazki o danym id
			MessageCreator messageCreator = new MessageCreator();
			ACLMessage message = messageCreator.orderBookMessage(booksTitle, ((InterfaceAgent) myAgent).getConnectedWarehouse());
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
			ACLMessage reply = ((InterfaceAgent) myAgent).getCurrentMessage();
			if (reply != null && messageTemplate.match(reply)) {
				ContentParser contentParser = new ContentParser(reply.getContent());
				if (reply.getPerformative() == ACLMessage.CONFIRM) {
					int booksId = contentParser.getBooksId();
					int booksTime = contentParser.getBooksTime();
					Date date = new Date();
					long time = date.getTime() + booksTime;
					date.setTime(time);
					System.out.println("Ksi¹¿ka o id: " + booksId + " zosta³a poprawnie zamówiona. Do odebrania " + date.toString());
				} else if (reply.getPerformative() == ACLMessage.DISCONFIRM) {
					System.out.println(contentParser.getBooksStatus() + ": " + contentParser.getError());
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
