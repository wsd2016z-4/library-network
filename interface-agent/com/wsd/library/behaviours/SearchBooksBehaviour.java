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
	private HashMap<BooksData, AID> bookIdWarehouseMap;
	private int warehouseNo;
	
	public SearchBooksBehaviour(String title) {
		super();
		this.title = title;
		bookIdWarehouseMap = new HashMap<BooksData, AID>();
	}

	@Override
	public void action() {
		switch (step) {
		case 0:
			// Wys³anie ACLMessage do wszystkich WarehouseAgent z pytaniem o ksiazke o danym tytule
			MessageCreator messageCreator = new MessageCreator();
			warehouseNo = ((InterfaceAgent) myAgent).getWarehouses().size();
			ACLMessage message = messageCreator.searchBookMessage(title, ((InterfaceAgent) myAgent).getWarehouses());
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
			ACLMessage reply = myAgent.receive(messageTemplate);
			if (reply != null) {
				if (reply.getPerformative() == ACLMessage.CONFIRM) {
					ContentParser contentParser = new ContentParser(reply.getContent());
					List<BooksData> books = contentParser.getBooksIdList();
					for (BooksData book : books) {
						bookIdWarehouseMap.put(book, reply.getSender());
					}
				} 
				warehouseNo--;
			}
			if (warehouseNo == 0) {
				// Wypisanie w GUI wszystkich znalezionych ksi¹¿ek wraz z Warehouse'ami w ktorych sie znajduja
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
