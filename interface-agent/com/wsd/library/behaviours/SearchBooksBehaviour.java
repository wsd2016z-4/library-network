package com.wsd.library.behaviours;

import java.util.HashMap;
import java.util.List;

import com.wsd.library.agent.InterfaceAgent;
import com.wsd.library.message.ContentParser;
import com.wsd.library.message.MessageCreator;
import com.wsd.library.model.BooksData;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class SearchBooksBehaviour extends Behaviour {

	private static final long serialVersionUID = -43590093686098595L;
	private String title;
	private int step = 0;
	private MessageTemplate messageTemplate;
	
	public SearchBooksBehaviour(String title) {
		super();
		this.title = title;
	}

	@Override
	public void action() {
		switch (step) {
		case 0:
			// Wys³anie ACLMessage do wszystkich WarehouseAgent z pytaniem o ksiazke o danym tytule
			MessageCreator messageCreator = new MessageCreator();
			ACLMessage message = messageCreator.searchBookMessage(title, ((InterfaceAgent) myAgent).getConnectedWarehouse());
			message.setSender(myAgent.getAID());
			message.setConversationId(BehaviourTypes.SEARCH_BOOKS);
			message.setReplyWith("request" + System.currentTimeMillis());
			myAgent.send(message);
			step = 1;
			messageTemplate = MessageTemplate.and(MessageTemplate.MatchConversationId(BehaviourTypes.SEARCH_BOOKS),
					MessageTemplate.MatchInReplyTo(message.getReplyWith()));
			break;
		case 1:
			// Odebranie odpowiedzi od agentów i dodanie do bookIdWarehouseMap
			ACLMessage reply = ((InterfaceAgent) myAgent).getCurrentMessage();
			if (reply != null && messageTemplate.match(reply)) {
				ContentParser contentParser = new ContentParser(reply.getContent());
				if (reply.getPerformative() == ACLMessage.CONFIRM) {
					List<BooksData> books = contentParser.getBooksList();
					step = 2;
				}
				// Wypisanie w GUI wszystkich znalezionych ksi¹¿ek
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
