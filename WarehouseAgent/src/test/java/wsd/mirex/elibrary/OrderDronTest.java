package wsd.mirex.elibrary;

import org.junit.Test;
import wsd.mirex.elibrary.message.orderDrone.*;

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
public class OrderDronTest
{
    @Test
    public void orderDronRequest() throws Exception
    {
        String xmlRequest = "<action name=\"orderDron\">\n" +
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

        OrderDroneRequest orderDronRequest;
        StringReader stringReader = new StringReader(xmlRequest);
        JAXBContext jaxbContext = JAXBContext.newInstance(OrderDroneRequest.class);
        XMLInputFactory xif = XMLInputFactory.newInstance();
        XMLStreamReader xsr = xif.createXMLStreamReader(stringReader);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        orderDronRequest = (OrderDroneRequest) unmarshaller.unmarshal(xsr);

        System.out.println("action:");
        System.out.println("\tname: " + orderDronRequest.getName());
        System.out.println("book:");
        System.out.println("\tid: " + orderDronRequest.getBook().getId());
        System.out.println("\tweight: " + orderDronRequest.getBook().getWeight());
        System.out.println("from:");
        System.out.println("\tid: " + orderDronRequest.getFrom().getId());
        System.out.println("\tlatitude: " + orderDronRequest.getFrom().getLatitude());
        System.out.println("\tlongitude: " + orderDronRequest.getFrom().getLongitude());
        System.out.println("to:");
        System.out.println("\tid: " + orderDronRequest.getTo().getId());
        System.out.println("\tlatitude: " + orderDronRequest.getTo().getLatitude());
        System.out.println("\tlongitude: " + orderDronRequest.getTo().getLongitude());
    }

    @Test
    public void orderDronResponse() throws Exception
    {
        AnswerResponse answerResponse = new AnswerResponse();
        answerResponse.setAgree("true");
        answerResponse.setTime(3600);

        OrderDroneResponse orderDronResponse = new OrderDroneResponse();
        orderDronResponse.setName("OrderDron");
        orderDronResponse.setAnswer(answerResponse);

        JAXBContext jaxbContext = JAXBContext.newInstance(OrderDroneResponse.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        StringWriter sw = new StringWriter();
        jaxbMarshaller.marshal(orderDronResponse, sw);
        String xmlString = sw.toString();
        System.out.println(xmlString);
    }

    @Test
    public void orderDronErrorResponse() throws Exception
    {
        AnswerErrorResponse answerErrorResponse = new AnswerErrorResponse();
        answerErrorResponse.setAgree("false");
        answerErrorResponse.setContent("Powod odmowy....");

        OrderDroneErrorResponse orderDronErrorResponse = new OrderDroneErrorResponse();
        orderDronErrorResponse.setName("OrderDron");
        orderDronErrorResponse.setAnswer(answerErrorResponse);

        JAXBContext jaxbContext = JAXBContext.newInstance(OrderDroneErrorResponse.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        StringWriter sw = new StringWriter();
        jaxbMarshaller.marshal(orderDronErrorResponse, sw);
        String xmlString = sw.toString();
        System.out.println(xmlString);
    }
}
