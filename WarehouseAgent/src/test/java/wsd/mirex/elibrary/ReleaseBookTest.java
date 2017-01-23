package wsd.mirex.elibrary;

import org.junit.Test;
import wsd.mirex.elibrary.message.releaseBook.*;

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
public class ReleaseBookTest
{
    @Test
    public void releaseBookRequest() throws Exception
    {
        String xmlRequest = "<action name=\"releaseBook\">\n" +
                "\t\t\t\t\t\t<book>\n" +
                "\t\t\t\t\t\t\t<id>2c5be756-60f7-4d48-af99-e43df42e3edb</id>\n" +
                "\t\t\t\t\t\t</book>\n" +
                "\t\t\t\t\t</action>";

        ReleaseBookRequest releaseBookRequest;
        StringReader stringReader = new StringReader(xmlRequest);
        JAXBContext jaxbContext = JAXBContext.newInstance(ReleaseBookRequest.class);
        XMLInputFactory xif = XMLInputFactory.newInstance();
        XMLStreamReader xsr = xif.createXMLStreamReader(stringReader);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        releaseBookRequest = (ReleaseBookRequest) unmarshaller.unmarshal(xsr);

        System.out.println("name: " + releaseBookRequest.getName());
        System.out.println("id: " + releaseBookRequest.getBook().getId());
    }

    @Test
    public void releaseBookResponse() throws Exception
    {
        BookResponse bookResponse = new BookResponse();
        bookResponse.setStatus("borrowed");
        bookResponse.setId("2c5be756-60f7-4d48-af99-e43df42e3edb");

        ReleaseBookResponse releaseBookResponse = new ReleaseBookResponse();
        releaseBookResponse.setName("releaseBook");
        releaseBookResponse.setBook(bookResponse);

        JAXBContext jaxbContext = JAXBContext.newInstance(ReleaseBookResponse.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        StringWriter sw = new StringWriter();
        jaxbMarshaller.marshal(releaseBookResponse, sw);
        String xmlString = sw.toString();
        System.out.println(xmlString);
    }

    @Test
    public void releaseBookErrorResponse() throws Exception
    {
        BookErrorResponse bookErrorResponse = new BookErrorResponse();
        bookErrorResponse.setStatus("error");
        bookErrorResponse.setContent("powód wystąpienia błędu...");

        ReleaseBookErrorResponse releaseBookErrorResponse = new ReleaseBookErrorResponse();
        releaseBookErrorResponse.setName("releaseBook");
        releaseBookErrorResponse.setBook(bookErrorResponse);

        JAXBContext jaxbContext = JAXBContext.newInstance(ReleaseBookErrorResponse.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        StringWriter sw = new StringWriter();
        jaxbMarshaller.marshal(releaseBookErrorResponse, sw);
        String xmlString = sw.toString();
        System.out.println(xmlString);
    }
}