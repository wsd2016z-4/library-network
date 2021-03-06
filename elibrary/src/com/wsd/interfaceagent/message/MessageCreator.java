package com.wsd.interfaceagent.message;

import java.io.StringWriter;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.Result;

import com.wsd.interfaceagent.model.BooksData;

import jade.core.AID;
import jade.lang.acl.ACLMessage;

public class MessageCreator {
	
	public ACLMessage searchBookMessage(String title, AID receiver) {
		ACLMessage message = new ACLMessage(ACLMessage.REQUEST);
		message.addReceiver(receiver);
		String content = "<action name=\"SearchBook\"><book><title>" 
											+ title + "</title></book></action>";
		message.setContent(content);
		return message;
	}
	
	public ACLMessage issueBookMessage(int bookId, AID receiver) {
		ACLMessage message = new ACLMessage(ACLMessage.REQUEST);
		message.addReceiver(receiver);
		String content = "<action name=\"IssueBook\"><book><id>" + bookId + "</id></book></action>";
		message.setContent(content);
		return message;
	}
	
	public ACLMessage orderBookMessage(String bookTitle, AID receiver) {
		ACLMessage message = new ACLMessage(ACLMessage.REQUEST);
		message.addReceiver(receiver);
		String content = "<action name=\"OrderBook\"><book><title>" + bookTitle + "</title></book></action>";
		message.setContent(content);
		return message;
	}
	
	public ACLMessage returnBookMessage(BooksData book, AID receiver) {
		ACLMessage message = new ACLMessage(ACLMessage.REQUEST);
		JAXBContext jaxbContext;
		String parsed = "";
		try {
			StringWriter sw = new StringWriter();
			jaxbContext = JAXBContext.newInstance(BooksData.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
			jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			jaxbMarshaller.marshal(book, sw);
			parsed = sw.toString();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		message.addReceiver(receiver);
		String content = "<action name=\"ReturnBook\">" + parsed + "</action>";
		message.setContent(content);
		return message;
	}
	
}
