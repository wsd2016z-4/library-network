package wsd.mirex.elibrary;

import org.junit.Test;
import wsd.mirex.elibrary.message.returnBookByDron.BookResponse;
import wsd.mirex.elibrary.message.returnBookByDron.ReturnBookByDronRequest;
import wsd.mirex.elibrary.message.returnBookByDron.ReturnBookByDronResponse;

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
public class ReturnBookByDronTest
{
    @Test
    public void returnBookByDronRequest() throws Exception
    {
        String xmlRequest = "<action name=\"returnBookByDron\"></action>";

        ReturnBookByDronRequest returnBookByDronRequest;
        StringReader stringReader = new StringReader(xmlRequest);
        JAXBContext jaxbContext = JAXBContext.newInstance(ReturnBookByDronRequest.class);
        XMLInputFactory xif = XMLInputFactory.newInstance();
        XMLStreamReader xsr = xif.createXMLStreamReader(stringReader);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        returnBookByDronRequest = (ReturnBookByDronRequest) unmarshaller.unmarshal(xsr);

        System.out.println("action:");
        System.out.println("\tname: " + returnBookByDronRequest.getName());
    }

    @Test
    public void returnBookByDronResponse() throws Exception
    {
        BookResponse bookResponse = new BookResponse();
        bookResponse.setStatus("ok");

        ReturnBookByDronResponse returnBookByDronResponse = new ReturnBookByDronResponse();
        returnBookByDronResponse.setName("returnBookByDron");
        returnBookByDronResponse.setBook(bookResponse);

        JAXBContext jaxbContext = JAXBContext.newInstance(ReturnBookByDronResponse.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        StringWriter sw = new StringWriter();
        jaxbMarshaller.marshal(returnBookByDronResponse, sw);
        String xmlString = sw.toString();
        System.out.println(xmlString);
    }

    @Test
    public void returnBookByDronErrorResponse() throws Exception
    {
        BookResponse bookResponse = new BookResponse();
        bookResponse.setStatus("error");
        bookResponse.setContent("powód wystąpienia błędu...");

        ReturnBookByDronResponse returnBookByDronResponse = new ReturnBookByDronResponse();
        returnBookByDronResponse.setName("returnBookByDron");
        returnBookByDronResponse.setBook(bookResponse);

        JAXBContext jaxbContext = JAXBContext.newInstance(ReturnBookByDronResponse.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        StringWriter sw = new StringWriter();
        jaxbMarshaller.marshal(returnBookByDronResponse, sw);
        String xmlString = sw.toString();
        System.out.println(xmlString);
    }
}
