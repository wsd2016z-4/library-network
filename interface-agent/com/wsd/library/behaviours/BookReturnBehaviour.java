package com.wsd.library.behaviours;

import com.wsd.library.DAO.BooksDAO;
import com.wsd.library.agent.InterfaceAgent;
import com.wsd.library.message.ContentParser;
import com.wsd.library.message.MessageCreator;
import com.wsd.library.model.BooksData;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class BookReturnBehaviour extends Behaviour {

	private static final long serialVersionUID = -350856938285818142L;

	private MessageTemplate messageTemplate;
	private int booksId;
	private String login;
	private int step = 0;
	private BooksDAO booksDAO;
	
	public BookReturnBehaviour(String login, int booksId) {
		super();
		this.booksId = booksId;
		this.login = login;
		booksDAO = new BooksDAO();
	}
	
	@Override
	public void action() {
		switch (step) {
		case 0:
			// Wys³anie ACLMessage do WarehouseAgent z pytaniem o ksiazke o danym tytule
			MessageCreator messageCreator = new MessageCreator();
			booksDAO.openCurrentSession();
			BooksData booksData = booksDAO.findById(booksId);
			booksDAO.closeCurrentSession();
			ACLMessage message = messageCreator.returnBookMessage(booksData, ((InterfaceAgent) myAgent).getConnectedWarehouse());
			message.setSender(myAgent.getAID());
			message.setConversationId(BehaviourTypes.BOOK_RETURN);
			message.setReplyWith("request" + System.currentTimeMillis());
			myAgent.send(message);
			step = 1;
			messageTemplate = MessageTemplate.and(MessageTemplate.MatchConversationId(BehaviourTypes.BOOK_RETURN),
					MessageTemplate.MatchInReplyTo(message.getReplyWith()));
			break;
		case 1:
			// Odebranie odpowiedzi od agenta
			ACLMessage reply = ((InterfaceAgent) myAgent).getCurrentMessage();
			if (reply != null && messageTemplate.match(reply)) {
				ContentParser contentParser = new ContentParser(reply.getContent());
				if (reply.getPerformative() == ACLMessage.CONFIRM) {
					System.out.println(contentParser.getBooksStatus() + ": Ksi¹¿ka o id: " + booksId + " zosta³a zamówiona.");
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
