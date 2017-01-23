package wsd.mirex.elibrary;

import org.junit.Test;
import wsd.mirex.elibrary.message.issueBook.IssueBookRequest;
import wsd.mirex.elibrary.message.issueBook.IssueBookResponse;
import wsd.mirex.elibrary.message.issueBook.BookResponse;
import wsd.mirex.elibrary.message.issueBook.IssueBookResponse;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * Created by pj on 22.01.17.
 */
public class IssueBookTest
{
    @Test
    public void issueBookRequest() throws Exception
    {
        String xmlRequest = "<action name=\"IssueBook\">\n" +
                "\t\t\t\t\t\t<book>\n" +
                "\t\t\t\t\t\t\t<id>2c5be756-60f7-4d48-af99-e43df42e3edb</id>\n" +
                "\t\t\t\t\t\t</book>\n" +
                "\t\t\t\t\t</action>";

        IssueBookRequest issueBookRequest;
        StringReader stringReader = new StringReader(xmlRequest);
        JAXBContext jaxbContext = JAXBContext.newInstance(IssueBookRequest.class);
        XMLInputFactory xif = XMLInputFactory.newInstance();
        XMLStreamReader xsr = xif.createXMLStreamReader(stringReader);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        issueBookRequest = (IssueBookRequest) unmarshaller.unmarshal(xsr);

        System.out.println("action:");
        System.out.println("\tname: " + issueBookRequest.getName());
        System.out.println("book:");
        System.out.println("\tid: " + issueBookRequest.getBook().getId());
    }

    @Test
    public void issueBookResponse() throws Exception
    {
        BookResponse bookResponse = new BookResponse();
        bookResponse.setStatus("ok");

        IssueBookResponse issueBookResponse = new IssueBookResponse();
        issueBookResponse.setName("IssueBook");
        issueBookResponse.setBook(bookResponse);

        JAXBContext jaxbContext = JAXBContext.newInstance(IssueBookResponse.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        StringWriter sw = new StringWriter();
        jaxbMarshaller.marshal(issueBookResponse, sw);
        String xmlString = sw.toString();
        System.out.println(xmlString);
    }

    @Test
    public void issueBookErrorResponse() throws Exception
    {
        BookResponse bookResponse = new BookResponse();
        bookResponse.setStatus("error");
        bookResponse.setContent("Opis błędu.");

        IssueBookResponse issueBookResponse = new IssueBookResponse();
        issueBookResponse.setName("IssueBook");
        issueBookResponse.setBook(bookResponse);

        JAXBContext jaxbContext = JAXBContext.newInstance(IssueBookResponse.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        StringWriter sw = new StringWriter();
        jaxbMarshaller.marshal(issueBookResponse, sw);
        String xmlString = sw.toString();
        System.out.println(xmlString);
    }
}