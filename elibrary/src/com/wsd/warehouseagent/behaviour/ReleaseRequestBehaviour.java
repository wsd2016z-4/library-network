package com.wsd.warehouseagent.behaviour;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import com.wsd.warehouseagent.helper.Singletons;
import com.wsd.warehouseagent.message.issueBook.IssueBookRequest;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.StringReader;

public class ReleaseRequestBehaviour extends Behaviour
{
    private String xmlRequest;
    private ACLMessage msg;
    private IssueBookRequest issueBookRequest;

    public ReleaseRequestBehaviour(String xmlRequest, ACLMessage msg)
    {
        this.xmlRequest = xmlRequest;
        this.msg = msg;
    }

    public void deserializeXmlToObject()
    {
        try
        {
            IssueBookRequest issueBookRequest;
            StringReader stringReader = new StringReader(xmlRequest);
            JAXBContext jaxbContext = JAXBContext.newInstance(IssueBookRequest.class);
            XMLInputFactory xif = XMLInputFactory.newInstance();
            XMLStreamReader xsr = xif.createXMLStreamReader(stringReader);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            issueBookRequest = (IssueBookRequest) unmarshaller.unmarshal(xsr);
            Singletons.setIssueBookRequest(issueBookRequest);
        }
        catch (JAXBException jaxb)
        {
            jaxb.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void serializeObjectToXml()
    {
//        try
//        {
//            new Serializable();
//        }
//        catch (JAXBException jaxb)
//        {
//            jaxb.printStackTrace();
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
    }

    @Override
    public void action()
    {
        deserializeXmlToObject();
        ACLMessage reply = new ACLMessage( ACLMessage.INFORM );
        reply.setContent( "<action name=\"IssueBook\">\n" +
                "<book>\n" +
                "<id>2c5be756-60f7-4d48-af99-e43df42e3edb</id>\n" +
                "</book>\n" +
                "</action>" );
        reply.setSender(myAgent.getAID());
        reply.addReceiver( msg.getSender() );
        myAgent.send(reply);

    }

    @Override
    public boolean done()
    {
        return true;
    }
}