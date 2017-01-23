package wsd.mirex.elibrary;

import org.junit.Test;
import wsd.mirex.elibrary.message.getLocalization.GetLocalizationRequest;
import wsd.mirex.elibrary.message.getLocalization.GetLocalizationResponse;

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
public class GetLocalizationTest
{
    @Test
    public void getLocalizationRequest() throws Exception
    {
        String xmlRequest = "<action name=\"getLocalization\"></action>";

        GetLocalizationRequest getLocalizationRequest;
        StringReader stringReader = new StringReader(xmlRequest);
        JAXBContext jaxbContext = JAXBContext.newInstance(GetLocalizationRequest.class);
        XMLInputFactory xif = XMLInputFactory.newInstance();
        XMLStreamReader xsr = xif.createXMLStreamReader(stringReader);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        getLocalizationRequest = (GetLocalizationRequest) unmarshaller.unmarshal(xsr);

        System.out.println("action:");
        System.out.println("\tname: " + getLocalizationRequest.getName());
    }

    @Test
    public void getLocalizationResponse() throws Exception
    {
        GetLocalizationResponse getLocalizationResponse = new GetLocalizationResponse();
        getLocalizationResponse.setId("2c5be756-60f7-4d48-af99-e43df42e3edb");
        getLocalizationResponse.setLatitude(22.196131);
        getLocalizationResponse.setLongitude(53.1498551);
        getLocalizationResponse.setName("getLocalization");

        JAXBContext jaxbContext = JAXBContext.newInstance(GetLocalizationResponse.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        StringWriter sw = new StringWriter();
        jaxbMarshaller.marshal(getLocalizationResponse, sw);
        String xmlString = sw.toString();
        System.out.println(xmlString);
    }
}
