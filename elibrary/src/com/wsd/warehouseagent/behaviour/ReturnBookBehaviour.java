package com.wsd.warehouseagent.behaviour;

import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import com.wsd.warehouseagent.dao.BookDao;
import com.wsd.warehouseagent.helper.WarehouseParameters;
import com.wsd.warehouseagent.message.returnBook.BookResponse;
import com.wsd.warehouseagent.message.returnBook.ReturnBookRequest;
import com.wsd.warehouseagent.message.returnBook.ReturnBookRespone;
import com.wsd.warehouseagent.model.Book;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * Created by pj on 23.01.17.
 */
public class ReturnBookBehaviour extends OneShotBehaviour
{
    private String xmlRequest;
    private ACLMessage msg;
    private ReturnBookRequest returnBookRequest;
    private BookDao bookDao;


    public ReturnBookBehaviour(ACLMessage msg)
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
            JAXBContext jaxbContext = JAXBContext.newInstance(ReturnBookRequest.class);
            XMLInputFactory xif = XMLInputFactory.newInstance();
            XMLStreamReader xsr = xif.createXMLStreamReader(stringReader);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            returnBookRequest = (ReturnBookRequest) unmarshaller.unmarshal(xsr);
        }
        catch (JAXBException jaxb)
        {
            jaxb.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void addBookToDB()
    {
        Book book = new Book();
        book.setIdBook(returnBookRequest.getBook().getId());
        book.setIsbn(returnBookRequest.getBook().getIsbn());
        book.setTitle(returnBookRequest.getBook().getTitle());
        book.setAuthor(returnBookRequest.getBook().getAuthor());
        book.setYear(returnBookRequest.getBook().getYear());
        book.setPrice(returnBookRequest.getBook().getPrice());
        book.setWeight(returnBookRequest.getBook().getWeight());
        book.setBookStatus("available ");
        bookDao.addBook(book);
    }

    public String serializObjectToXmlOk()
    {
        try
        {
            BookResponse bookResponse = new BookResponse();
            bookResponse.setStatus("ok");

            ReturnBookRespone returnBookRespone = new ReturnBookRespone();
            returnBookRespone.setName("ReturnBook");
            returnBookRespone.setBook(bookResponse);

            JAXBContext jaxbContext = JAXBContext.newInstance(ReturnBookRespone.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(returnBookRespone, sw);
            String xmlString = sw.toString();

            return xmlString;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public String serializObjectToXmlError()
    {
        try
        {
            BookResponse bookResponse = new BookResponse();
            bookResponse.setStatus("error");
            bookResponse.setContent("Brak miejsca w automacie...");

            ReturnBookRespone returnBookRespone = new ReturnBookRespone();
            returnBookRespone.setName("ReturnBook");
            returnBookRespone.setBook(bookResponse);

            JAXBContext jaxbContext = JAXBContext.newInstance(ReturnBookRespone.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(returnBookRespone, sw);
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

        if (bookDao.getBooksCount() <= WarehouseParameters.MAX_BOOKS_COUNT)
        {
            addBookToDB();
            ACLMessage reply = new ACLMessage( ACLMessage.CONFIRM );
            reply.setContent(serializObjectToXmlOk());
            reply.setSender(myAgent.getAID());
            reply.addReceiver( msg.getSender() );
            myAgent.send(reply);
        }
        else
        {
            ACLMessage reply = new ACLMessage( ACLMessage.DISCONFIRM );
            reply.setContent(serializObjectToXmlError());
            reply.setSender(myAgent.getAID());
            reply.addReceiver( msg.getSender() );
            myAgent.send(reply);
        }
    }
}