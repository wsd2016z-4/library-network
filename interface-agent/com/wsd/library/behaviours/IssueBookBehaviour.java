package com.wsd.library.behaviours;

import java.util.List;

import com.wsd.library.agent.InterfaceAgent;
import com.wsd.library.message.ContentParser;
import com.wsd.library.message.MessageCreator;
import com.wsd.library.model.BooksData;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class IssueBookBehaviour extends Behaviour {

	public IssueBookBehaviour(int bookId) {
		super();
		this.bookId = bookId;
	}

	private static final long serialVersionUID = -6442036378935057101L;
	
	private MessageTemplate messageTemplate;
	private int bookId;
	private int step = 0;
	
	@Override
	public void action() {
		switch (step) {
		case 0:
			// Wys³anie ACLMessage do powi¹zanego WarehouseAgent z pytaniem o ksiazke o danym tytule
			MessageCreator messageCreator = new MessageCreator();
			ACLMessage message = messageCreator.issueBookMessage(bookId, ((InterfaceAgent) myAgent).getConnectedWarehouse());
			message.setSender(myAgent.getAID());
			message.setConversationId(BehaviourTypes.ISSUE_BOOK);
			message.setReplyWith("request" + System.currentTimeMillis());
			myAgent.send(message);
			step = 1;
			messageTemplate = MessageTemplate.and(MessageTemplate.MatchConversationId(BehaviourTypes.ISSUE_BOOK),
					MessageTemplate.MatchInReplyTo(message.getReplyWith()));
			break;
		case 1:
			// Odebranie odpowiedzi od agenta
			ACLMessage reply = myAgent.receive(messageTemplate);
			if (reply != null) {
				if (reply.getPerformative() == ACLMessage.CONFIRM) {
					step = 2;
					ContentParser contentParser = new ContentParser(reply.getContent());
					BooksData book = contentParser.getBook();
					System.out.println("Wydano ksi¹¿kê - " + book.getTitle());
				} else if (reply.getPerformative() == ACLMessage.DISCONFIRM) {
					step = 2;
					System.out.println("B³¹d przy wydawaniu ksi¹¿ki");
				}
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
