package com.wsd.library.message;

import java.util.List;

import jade.core.AID;
import jade.lang.acl.ACLMessage;

public class MessageCreator {
	
	public ACLMessage searchBookMessage(String title, List<AID> receivers) {
		ACLMessage message = new ACLMessage(ACLMessage.REQUEST);
		for (AID receiver : receivers)
			message.addReceiver(receiver);
		String content = "<action name=\"searchBooks\"><title>" + title + "</title></action>";
		message.setContent(content);
		return message;
	}
	
}
