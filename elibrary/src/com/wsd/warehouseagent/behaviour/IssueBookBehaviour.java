package com.wsd.warehouseagent.behaviour;

import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import com.wsd.warehouseagent.dao.BookDao;
import com.wsd.warehouseagent.message.issueBook.BookResponse;
import com.wsd.warehouseagent.message.issueBook.IssueBookRequest;
import com.wsd.warehouseagent.message.issueBook.IssueBookResponse;
import com.wsd.warehouseagent.model.Book;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * Created by pj on 23.01.17.
 */
public class IssueBookBehaviour extends OneShotBehaviour
{
    private String xmlRequest;
    private ACLMessage msg;
    private IssueBookRequest issueBookRequest;
    private BookDao bookDao;

    public IssueBookBehaviour(ACLMessage msg)
    {
        xmlRequest = msg.getContent();
        this.msg = msg;
        bookDao = new BookDao();
    }

    public void deserializeXmlToObject()
    {
        try
        {
            StringReader stringReader = new StringReader(xmlRequest);
            JAXBContext jaxbContext = JAXBContext.newInstance(IssueBookRequest.class);
            XMLInputFactory xif = XMLInputFactory.newInstance();
            XMLStreamReader xsr = xif.createXMLStreamReader(stringReader);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            issueBookRequest = (IssueBookRequest) unmarshaller.unmarshal(xsr);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public Book getBookFromDB()
    {
        Book book = bookDao.findByIdBook(issueBookRequest.getBook().getId());
        bookDao.deleteBook(book);

        return book;
    }

    public String serializeObiectToXmlOk()
    {
        try
        {
            BookResponse bookResponseLocal = new BookResponse();
            bookResponseLocal.setStatus("ok");

            IssueBookResponse issueBookResponseLocal = new IssueBookResponse();
            issueBookResponseLocal.setName("IssueBook");
            issueBookResponseLocal.setBook(bookResponseLocal);

            JAXBContext jaxbContext = JAXBContext.newInstance(IssueBookResponse.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(issueBookResponseLocal, sw);
            String xmlString = sw.toString();
            return xmlString;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }

    }

    public String serializeObjectToXmlError()
    {
        try
        {
            BookResponse bookResponseLocal = new BookResponse();
            bookResponseLocal.setStatus("error");
            bookResponseLocal.setContent("Wskazana ksiazka nie jest dostepna w automacie...");

            IssueBookResponse issueBookResponseLocal = new IssueBookResponse();
            issueBookResponseLocal.setName("IssueBook");
            issueBookResponseLocal.setBook(bookResponseLocal);

            JAXBContext jaxbContext = JAXBContext.newInstance(IssueBookResponse.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(issueBookResponseLocal, sw);
            String xmlString = sw.toString();
            return xmlString;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void action()
    {
        deserializeXmlToObject();
        Book book = getBookFromDB();

        if (null != book)
        {
            ACLMessage reply = new ACLMessage( ACLMessage.CONFIRM );
            reply.setContent(serializeObiectToXmlOk());
            reply.setSender(myAgent.getAID());
            reply.addReceiver( msg.getSender() );
            myAgent.send(reply);
        }
        else
        {
            ACLMessage reply = new ACLMessage( ACLMessage.DISCONFIRM );
            reply.setContent(serializeObjectToXmlError());
            reply.setSender(myAgent.getAID());
            reply.addReceiver( msg.getSender() );
            myAgent.send(reply);
        }
    }
}
