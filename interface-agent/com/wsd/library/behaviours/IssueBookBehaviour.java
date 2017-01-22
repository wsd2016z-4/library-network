package com.wsd.library.behaviours;

import java.util.Date;
import java.util.List;

import com.wsd.library.DAO.UsersUptakesDAO;
import com.wsd.library.agent.InterfaceAgent;
import com.wsd.library.message.ContentParser;
import com.wsd.library.message.MessageCreator;
import com.wsd.library.model.BooksData;
import com.wsd.library.model.UserData;
import com.wsd.library.model.UsersUptakesData;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class IssueBookBehaviour extends Behaviour {

	public IssueBookBehaviour(int bookId, int userId) {
		super();
		this.bookId = bookId;
		this.userId = userId;
		usersUptakesDAO = new UsersUptakesDAO();
	}

	private static final long serialVersionUID = -6442036378935057101L;
	
	private UsersUptakesDAO usersUptakesDAO;
	
	private MessageTemplate messageTemplate;
	private int bookId;
	private int userId;
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
			// Interpretacja otrzymanej odpowiedzi
			ACLMessage reply = ((InterfaceAgent) myAgent).getCurrentMessage();
			if (reply != null && messageTemplate.match(reply)) {
				ContentParser contentParser = new ContentParser(reply.getContent());
				if (reply.getPerformative() == ACLMessage.CONFIRM) {
					System.out.println(contentParser.getBooksStatus() + ": Wydano ksi¹¿kê - " + contentParser.getBook().getTitle());
					UsersUptakesData userUptakesData = new UsersUptakesData();
					userUptakesData.setBooksData(contentParser.getBook());
					userUptakesData.setStartDate(new Date());
					UserData userData = new UserData();
					userData.setId(userId);
					userUptakesData.setUserData(userData);
					usersUptakesDAO.openCurrentSessionwithTransaction();
					usersUptakesDAO.persist(userUptakesData);
					usersUptakesDAO.closeCurrentSessionwithTransaction();
				} else if (reply.getPerformative() == ACLMessage.DISCONFIRM) 
					System.out.println(contentParser.getBooksStatus() + ": " + contentParser.getError());
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
