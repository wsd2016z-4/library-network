package com.wsd.warehouseagent.behaviour;

import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import com.wsd.warehouseagent.message.checkAvailability.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * Created by pj on 23.01.17.
 */
public class CheckAvailabilityBehaviour extends OneShotBehaviour
{
//    private String xmlRequest;
//    private ACLMessage msg;
//    private  issueBookRequest;
//    private BookDao bookDao;

    public CheckAvailabilityBehaviour(ACLMessage msg) {
    }

    public String serializeObjectToXmlRequest()
    {
        try
        {
            BookRequest bookRequest = new BookRequest();
            bookRequest.setId("2c5be756-60f7-4d48-af99-e43df42e3edb");
            bookRequest.setWeight(200);

            FromRequest fromRequest = new FromRequest();
            fromRequest.setId("2c5be756-60f7-4d48-af99-e43df42e3edb");
            fromRequest.setLatitude(21.196131);
            fromRequest.setLongitude(52.1498551);

            ToRequest toRequest = new ToRequest();
            toRequest.setId("2c5be756-60f7-4d48-af99-e43df42e3edb");
            toRequest.setLatitude(22.196131);
            toRequest.setLongitude(53.1498551);

            CheckAvailabilityRequest checkAvailabilityRequest = new CheckAvailabilityRequest();
            checkAvailabilityRequest.setName("CheckAvailability");
            checkAvailabilityRequest.setBook(bookRequest);
            checkAvailabilityRequest.setFrom(fromRequest);
            checkAvailabilityRequest.setTo(toRequest);

            JAXBContext jaxbContext = JAXBContext.newInstance(CheckAvailabilityRequest.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(checkAvailabilityRequest, sw);
            String xmlString = sw.toString();
            return xmlString;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public CheckAvailabilityResponse deserializeXmlToObjectOk(String xmlRequest)
    {
        try {
            CheckAvailabilityResponse checkAvailabilityResponse;
            StringReader stringReader = new StringReader(xmlRequest);
            JAXBContext jaxbContext = JAXBContext.newInstance(CheckAvailabilityResponse.class);
            XMLInputFactory xif = XMLInputFactory.newInstance();
            XMLStreamReader xsr = xif.createXMLStreamReader(stringReader);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            checkAvailabilityResponse = (CheckAvailabilityResponse) unmarshaller.unmarshal(xsr);
            return checkAvailabilityResponse;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public CheckAvailabilityErrorResponse deserializeXmlToObjectError(String xmlRequest)
    {
        try
        {
            CheckAvailabilityErrorResponse checkAvailabilityErrorResponse;
            StringReader stringReader = new StringReader(xmlRequest);
            JAXBContext jaxbContext = JAXBContext.newInstance(CheckAvailabilityErrorResponse.class);
            XMLInputFactory xif = XMLInputFactory.newInstance();
            XMLStreamReader xsr = xif.createXMLStreamReader(stringReader);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            checkAvailabilityErrorResponse = (CheckAvailabilityErrorResponse) unmarshaller.unmarshal(xsr);
            return checkAvailabilityErrorResponse;
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
        ACLMessage msg = new ACLMessage( ACLMessage.DISCONFIRM );
        msg.setContent(serializeObjectToXmlRequest());
        msg.setSender(myAgent.getAID());
//        reply.addReceiver( "" );
        myAgent.send(msg);
    }
}