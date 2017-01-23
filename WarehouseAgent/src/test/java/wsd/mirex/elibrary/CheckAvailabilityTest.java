package wsd.mirex.elibrary;

import org.junit.Test;
import wsd.mirex.elibrary.message.checkAvailability.*;

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
public class CheckAvailabilityTest
{
    @Test
    public void checkAvailabilityRequest() throws Exception
    {
        String xmlRequest = "<action name=\"checkAvailability\">\n" +
                "\t\t\t\t\t\t<book>\n" +
                "\t\t\t\t\t\t\t<id>2c5be756-60f7-4d48-af99-e43df42e3edb</id>\n" +
                "\t\t\t\t\t\t\t<weight>200</weight>\n" +
                "\t\t\t\t\t\t</book>\n" +
                "\t\t\t\t\t\t<from>\n" +
                "\t\t\t\t\t\t\t<id>2c5be756-60f7-4d48-af99-e43df42e3edb</id><!--wherehoutse agent-->\n" +
                "\t\t\t\t\t\t\t<latitude>21.196131</latitude>\n" +
                "\t\t\t\t\t\t\t<longitude>52.1498551</longitude>\n" +
                "\t\t\t\t\t\t</from>\n" +
                "\t\t\t\t\t\t<to>\n" +
                "\t\t\t\t\t\t\t<id>2c5be756-60f7-4d48-af99-e43df42e3edb</id><!--wherehoutse agent-->\n" +
                "\t\t\t\t\t\t\t<latitude>22.196131</latitude>\n" +
                "\t\t\t\t\t\t\t<longitude>53.1498551</longitude>\n" +
                "\t\t\t\t\t\t</to>\n" +
                "\t\t\t\t\t</action>";

        CheckAvailabilityRequest checkAvailabilityRequest;
        StringReader stringReader = new StringReader(xmlRequest);
        JAXBContext jaxbContext = JAXBContext.newInstance(CheckAvailabilityRequest.class);
        XMLInputFactory xif = XMLInputFactory.newInstance();
        XMLStreamReader xsr = xif.createXMLStreamReader(stringReader);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        checkAvailabilityRequest = (CheckAvailabilityRequest) unmarshaller.unmarshal(xsr);

        System.out.println("action:");
        System.out.println("\tname: " + checkAvailabilityRequest.getName());
        System.out.println("book:");
        System.out.println("\tid: " + checkAvailabilityRequest.getBook().getId());
        System.out.println("\tweight: " + checkAvailabilityRequest.getBook().getWeight());
        System.out.println("from:");
        System.out.println("\tid: " + checkAvailabilityRequest.getFrom().getId());
        System.out.println("\tlatitude: " + checkAvailabilityRequest.getFrom().getLatitude());
        System.out.println("\tlongitude: " + checkAvailabilityRequest.getFrom().getLongitude());
        System.out.println("to:");
        System.out.println("\tid: " + checkAvailabilityRequest.getTo().getId());
        System.out.println("\tlatitude: " + checkAvailabilityRequest.getTo().getLatitude());
        System.out.println("\tlongitude: " + checkAvailabilityRequest.getTo().getLongitude());
    }

    @Test
    public void checkAvailabilityResponse() throws Exception
    {
        AnswerResponse answerResponse = new AnswerResponse();
        answerResponse.setAgree("true");
        answerResponse.setWaiting("500");
        answerResponse.setTime(3600);

        CheckAvailabilityResponse checkAvailabilityResponse = new CheckAvailabilityResponse();
        checkAvailabilityResponse.setName("CheckAvailability");
        checkAvailabilityResponse.setAnswer(answerResponse);

        JAXBContext jaxbContext = JAXBContext.newInstance(CheckAvailabilityResponse.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        StringWriter sw = new StringWriter();
        jaxbMarshaller.marshal(checkAvailabilityResponse, sw);
        String xmlString = sw.toString();
        System.out.println(xmlString);
    }

    @Test
    public void checkAvailabilityErrorResponse() throws Exception
    {
        AnswerErrorResponse answerErrorResponse = new AnswerErrorResponse();
        answerErrorResponse.setAgree("false");
        answerErrorResponse.setContent("powód odmowy....");

        CheckAvailabilityErrorResponse checkAvailabilityErrorResponse = new CheckAvailabilityErrorResponse();
        checkAvailabilityErrorResponse.setName("CheckAvailability");
        checkAvailabilityErrorResponse.setAnswer(answerErrorResponse);

        JAXBContext jaxbContext = JAXBContext.newInstance(CheckAvailabilityErrorResponse.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        StringWriter sw = new StringWriter();
        jaxbMarshaller.marshal(checkAvailabilityErrorResponse, sw);
        String xmlString = sw.toString();
        System.out.println(xmlString);
    }
}