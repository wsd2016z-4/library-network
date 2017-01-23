package wsd.mirex.elibrary;

import org.junit.Test;
import wsd.mirex.elibrary.message.getBook.AnswerResponse;
import wsd.mirex.elibrary.message.getBook.GetBookRequest;
import wsd.mirex.elibrary.message.getBook.GetBookResponse;

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
public class GetBookTest
{
    @Test
    public void getBookRequest() throws Exception
    {
        String xmlRequest = "<action name=\"getBook\">\n" +
                "\t\t\t\t\t\t<book>\n" +
                "\t\t\t\t\t\t\t<id>2c5be756-60f7-4d48-af99-e43df42e3edb</id>\n" +
                "\t\t\t\t\t\t</book>\n" +
                "\t\t\t\t\t</action>";

        GetBookRequest getBookRequest;
        StringReader stringReader = new StringReader(xmlRequest);
        JAXBContext jaxbContext = JAXBContext.newInstance(GetBookRequest.class);
        XMLInputFactory xif = XMLInputFactory.newInstance();
        XMLStreamReader xsr = xif.createXMLStreamReader(stringReader);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        getBookRequest = (GetBookRequest) unmarshaller.unmarshal(xsr);

        System.out.println("action:");
        System.out.println("\tname: " + getBookRequest.getName());
        System.out.println("book:");
        System.out.println("\tid: " + getBookRequest.getBook().getId());
    }

    @Test
    public void getBookResponse() throws Exception
    {
        AnswerResponse answerResponse= new AnswerResponse();
        answerResponse.setAgree("true");
        GetBookResponse getBookResponse = new GetBookResponse();
        getBookResponse.setName("getBook");
        getBookResponse.setAnswer(answerResponse);

        JAXBContext jaxbContext = JAXBContext.newInstance(GetBookResponse.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        StringWriter sw = new StringWriter();
        jaxbMarshaller.marshal(getBookResponse, sw);
        String xmlString = sw.toString();
        System.out.println(xmlString);
    }

    @Test
    public void getBookErrorResponse() throws Exception
    {
        AnswerResponse answerResponse= new AnswerResponse();
        answerResponse.setAgree("false");
        answerResponse.setContent("powód odmowy....");
        GetBookResponse getBookResponse = new GetBookResponse();
        getBookResponse.setName("getBook");
        getBookResponse.setAnswer(answerResponse);

        JAXBContext jaxbContext = JAXBContext.newInstance(GetBookResponse.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        StringWriter sw = new StringWriter();
        jaxbMarshaller.marshal(getBookResponse, sw);
        String xmlString = sw.toString();
        System.out.println(xmlString);
    }
}
