package com.wsd.library.message;

import java.util.List;

import jade.core.AID;
import jade.lang.acl.ACLMessage;

public class MessageCreator {
	
	public ACLMessage searchBookMessage(String title, List<AID> receivers) {
		ACLMessage message = new ACLMessage(ACLMessage.REQUEST);
		for (AID receiver : receivers)
			message.addReceiver(receiver);
		String content = "<action name=\"searchBooks\"><book status=\"available\"><title>" 
											+ title + "</title></book></action>";
		message.setContent(content);
		return message;
	}
	
	public ACLMessage issueBookMessage(int bookId, AID receiver) {
		ACLMessage message = new ACLMessage(ACLMessage.REQUEST);
		message.addReceiver(receiver);
		String content = "<action name=\"issueBook\"><bookid>" + bookId + "</bookid></action>";
		message.setContent(content);
		return message;
	}
	
}
