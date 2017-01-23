package wsd.mirex.elibrary;

import org.junit.Test;
import wsd.mirex.elibrary.message.orderBook.*;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * Created by pj on 21.01.17.
 */
public class OrderBookTest
{
    @Test
    public void orderBookRequest() throws Exception
    {
        String xmlRequest = "<action name=\"OrderBook\">\n" +
                "<book>\n" +
                "<title>Everyday Italian</title>\n" +
                "</book>\n" +
                "</action>";

        OrderBookRequest orderBookRequest;
        StringReader stringReader = new StringReader(xmlRequest);
        JAXBContext jaxbContext = JAXBContext.newInstance(OrderBookRequest.class);
        XMLInputFactory xif = XMLInputFactory.newInstance();
        XMLStreamReader xsr = xif.createXMLStreamReader(stringReader);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        orderBookRequest = (OrderBookRequest) unmarshaller.unmarshal(xsr);

        System.out.println("action:");
        System.out.println("\tname: " + orderBookRequest.getName());
        System.out.println("book");
        System.out.println("\ttitle: " + orderBookRequest.getBook().getTitle());
    }

    @Test
    public void orderBookResponse() throws Exception
    {
        BookResponse bookResponse = new BookResponse();
        bookResponse.setStatus("ordered");
        bookResponse.setId("2c5be756-60f7-4d48-af99-e43df42e3edb");
        bookResponse.setTime(3600);

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
        System.out.println(xmlString);
    }

    @Test
    public void orderBookErrorResponse() throws Exception
    {
        BookErrorResponse bookErrorResponse = new BookErrorResponse();
        bookErrorResponse.setStatus("error");
        bookErrorResponse.setContent("powód wystąpienia błędu");

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
        System.out.println(xmlString);
    }
}