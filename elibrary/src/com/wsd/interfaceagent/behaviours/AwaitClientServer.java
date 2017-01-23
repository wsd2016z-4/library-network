package com.wsd.interfaceagent.behaviours;

import java.math.BigDecimal;

import com.wsd.interfaceagent.message.ContentParser;
import com.wsd.interfaceagent.model.UserData;
import com.wsd.interfaceagent.agent.InterfaceAgent;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

// Ten behaviour dziala non stop i wyzwala wszystkie inne
public class AwaitClientServer extends CyclicBehaviour {

	private static final long serialVersionUID = -5820738946553366666L;
	
	@Override
	public void action() {
		ACLMessage msg = myAgent.receive();
		((InterfaceAgent) myAgent).setCurrentMessage(msg);
		if (msg != null) {
			// Tutaj wyci¹gamy dane z wiadomoœci i procesujemy odpowiedni behaviour
			// AID msgSender = msg.getSender(); 	
			String msgContent = msg.getContent();
			ContentParser contentParser = new ContentParser(msgContent);
			String actionType = contentParser.getActionType();
			switch (actionType) {
				case BehaviourTypes.LOG_USER:
					myAgent.addBehaviour(new LogUserBehaviour(contentParser.getRootsChildValue(ContentParser.CHILD_LOGIN), 
															contentParser.getRootsChildValue(ContentParser.CHILD_PASS)));
					break;
				case BehaviourTypes.CHECK_LIMIT:
					myAgent.addBehaviour(new CheckLimitBehaviour(contentParser.getRootsChildValue(ContentParser.CHILD_LOGIN)));
					break;
				case BehaviourTypes.CREATE_ACCOUNT:
					myAgent.addBehaviour(new CreateAccountBehaviour(getUserModelFromMsg(contentParser)));
					break;
				case BehaviourTypes.PAY:
					myAgent.addBehaviour(new PayBehaviour(contentParser.getRootsChildValue(ContentParser.CHILD_LOGIN),
							new BigDecimal(contentParser.getRootsChildValue(ContentParser.CHILD_PRICE))));
					break;
				case BehaviourTypes.TRANSFER_MONEY:
					myAgent.addBehaviour(new TransferMoneyBehaviour(contentParser.getRootsChildValue(ContentParser.CHILD_LOGIN),
							new BigDecimal(contentParser.getRootsChildValue(ContentParser.CHILD_AMOUNT))));
					break;
				case BehaviourTypes.WRITE_HISTORY:
					myAgent.addBehaviour(new WriteHistoryBehaviour(Integer.valueOf(contentParser.getRootsChildValue(ContentParser.CHILD_USERID)),
							Integer.valueOf(contentParser.getRootsChildValue(ContentParser.CHILD_BOOKID))));
					break;
				case BehaviourTypes.SEARCH_BOOKS:
					if (msg.getPerformative() == ACLMessage.REQUEST)
						myAgent.addBehaviour(new SearchBooksBehaviour(contentParser.getRootsChildValue(ContentParser.CHILD_TITLE)));
					break;
				case BehaviourTypes.ISSUE_BOOK:
					myAgent.addBehaviour(new IssueBookBehaviour(Integer.valueOf(contentParser.getRootsChildValue(ContentParser.CHILD_BOOKID)), Integer.valueOf(contentParser.getRootsChildValue(ContentParser.CHILD_USERID))));
					break;
				case BehaviourTypes.BOOK_RETURN:
					myAgent.addBehaviour(new BookReturnBehaviour(contentParser.getRootsChildValue(ContentParser.CHILD_LOGIN), Integer.valueOf(contentParser.getRootsChildValue(ContentParser.CHILD_BOOKID))));
					break;
				case BehaviourTypes.ORDER_BOOK:	
					myAgent.addBehaviour(new OrderBookBehaviour(contentParser.getBooksTitle()));
					break;
				default:
					System.out.println("No action type.");
					break;
			}	
		} else {
			block();
		}
		//System.out.println("Waiting for messages.");
	}
	
	// TODO ta metoda tutaj nie pasuje
	private UserData getUserModelFromMsg(ContentParser contentParser) {
		UserData userData = new UserData();
		userData.setUserLogin(contentParser.getRootsChildValue(ContentParser.CHILD_LOGIN));
		userData.setUserPass(contentParser.getRootsChildValue(ContentParser.CHILD_PASS));
		userData.setName(contentParser.getRootsChildValue(ContentParser.CHILD_NAME));
		userData.setSurname(contentParser.getRootsChildValue(ContentParser.CHILD_SURNAME));
		userData.setAddress(contentParser.getRootsChildValue(ContentParser.CHILD_ADDRESS));
		return userData;
	}

}
