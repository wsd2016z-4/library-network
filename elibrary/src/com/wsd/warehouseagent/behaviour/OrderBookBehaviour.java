package com.wsd.warehouseagent.behaviour;

import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import com.wsd.warehouseagent.dao.BookDao;
import com.wsd.warehouseagent.message.orderBook.*;
import com.wsd.warehouseagent.model.Book;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by pj on 23.01.17.
 */
public class OrderBookBehaviour extends OneShotBehaviour
{
    private String xmlRequest;
    private ACLMessage msg;
    private OrderBookRequest orderBookRequest;
    private BookDao bookDao;
    private Book book;

    public OrderBookBehaviour(ACLMessage msg)
    {
        xmlRequest = msg.getContent();
        bookDao = new BookDao();
        this.msg = msg;
    }

    public Book reserveBook()
    {
        List<Book> books = bookDao.findByTitle(orderBookRequest.getBook().getTitle());
        book = null;

        for(int i = 0; i < books.size(); ++i)
        {
            if(books.get(i).getBookStatus().equals("available"))
                book = books.get(i);
        }

        if (null != book)
        {
            book.setBookStatus("ordered");
            bookDao.updateBook(book);
            return book;
        }
        else
        {
            return null;
        }

    }

    public void deserializeXmlToObject()
    {
        try
        {
            StringReader stringReader = new StringReader(xmlRequest);
            JAXBContext jaxbContext = JAXBContext.newInstance(OrderBookRequest.class);
            XMLInputFactory xif = XMLInputFactory.newInstance();
            XMLStreamReader xsr = xif.createXMLStreamReader(stringReader);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            orderBookRequest = (OrderBookRequest) unmarshaller.unmarshal(xsr);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public String serializeObjectToXmlOk()
    {
        try
        {
            BookResponse bookResponse = new BookResponse();
            bookResponse.setStatus("ordered");
            bookResponse.setId(book.getIdBook());
            bookResponse.setTime(ThreadLocalRandom.current().nextInt(0, 15000));

            OrderBookResponse orderBookResponse = new OrderBookResponse();
            orderBookResponse.setName("OrderBook");
            orderBookResponse.setBook(bookResponse);

            JAXBContext jaxbContext = JAXBContext.newInstance(OrderBookResponse.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(orderBookResponse, sw);
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
            BookErrorResponse bookErrorResponse = new BookErrorResponse();
            bookErrorResponse.setStatus("error");
            bookErrorResponse.setContent("Brak dostepnej ksiazki o wskazanym ID...");

            OrderBookErrorResponse orderBookErrorResponse = new OrderBookErrorResponse();
            orderBookErrorResponse.setName("OrderBook");
            orderBookErrorResponse.setBook(bookErrorResponse);

            JAXBContext jaxbContext = JAXBContext.newInstance(OrderBookErrorResponse.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(orderBookErrorResponse, sw);
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

        if(null != reserveBook())
        {
            ACLMessage reply = new ACLMessage( ACLMessage.CONFIRM );
            reply.setContent(serializeObjectToXmlOk());
            reply.setSender(myAgent.getAID());
            reply.addReceiver( msg.getSender() );
            myAgent.send(reply);
            System.out.println(reply.toString());
        }
        else
        {
            ACLMessage reply = new ACLMessage( ACLMessage.DISCONFIRM);
            reply.setContent(serializeObjectToXmlError());
            reply.setSender(myAgent.getAID());
            reply.addReceiver( msg.getSender() );
            myAgent.send(reply);
            System.out.println(reply.toString());
        }
    }
}