package wsd.mirex.elibrary;

import org.junit.Test;
import wsd.mirex.elibrary.message.returnBook.*;

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
public class ReturnBookTest
{
    @Test
    public void returnBookRequest() throws Exception
    {
        String xmlRequest = "<action name=\"ReturnBook\">\n" +
                "<book>\n" +
                "<id>2c5be756-60f7-4d48-af99-e43df42e3edb</id>\n" +
                "\t\t\t\t\t<isbn>978-2-1234-5680-3</isbn>\n" +
                "\t\t\t\t\t<title>Everyday Italian</title>\n" +
                "\t\t\t\t\t<author>Giada De Laurentiis</author>\t\t\t\t\t\t\t\t<year>2005</year>\n" +
                "<price>30.00</price>\t\t\t\t\t<weight>200</weight>\n" +
                "</book>\n" +
                "</action>";

        ReturnBookRequest returnBookRequest;
        StringReader stringReader = new StringReader(xmlRequest);
        JAXBContext jaxbContext = JAXBContext.newInstance(ReturnBookRequest.class);
        XMLInputFactory xif = XMLInputFactory.newInstance();
        XMLStreamReader xsr = xif.createXMLStreamReader(stringReader);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        returnBookRequest = (ReturnBookRequest) unmarshaller.unmarshal(xsr);

        System.out.println("action: ");
        System.out.println("\tname: " + returnBookRequest.getName());
        System.out.println("book: ");
        System.out.println("\tid: " + returnBookRequest.getBook().getId());
        System.out.println("\tisbn: " + returnBookRequest.getBook().getIsbn());
        System.out.println("\ttitle: " + returnBookRequest.getBook().getTitle());
        System.out.println("\tauthor: " + returnBookRequest.getBook().getAuthor());
        System.out.println("\tyear: " + returnBookRequest.getBook().getYear());
        System.out.println("\tprice: " + returnBookRequest.getBook().getPrice());
        System.out.println("\tweight: " + returnBookRequest.getBook().getWeight());
    }

    @Test
    public void returnBookResponse() throws Exception
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
        System.out.println(xmlString);
    }

    @Test
    public void returnBookErrorResponse() throws Exception
    {
        BookResponse bookResponse = new BookResponse();
        bookResponse.setStatus("error");
        bookResponse.setContent("Opis bledu...");

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
        System.out.println(xmlString);
    }
}