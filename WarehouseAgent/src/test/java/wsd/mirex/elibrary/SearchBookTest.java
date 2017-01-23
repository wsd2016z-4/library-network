package wsd.mirex.elibrary;

import org.junit.Test;
import wsd.mirex.elibrary.message.searchBook.BookResponse;
import wsd.mirex.elibrary.message.searchBook.SearchBookRequest;
import wsd.mirex.elibrary.message.searchBook.SearchBookResponse;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pj on 21.01.17.
 */
public class SearchBookTest
{
    @Test
    public void searchBookRequest() throws Exception
    {
        String xmlRequest = "<action name=\"searchBooks\">\n" +
                "\t\t\t\t  \t\t<book>\n" +
                "\t\t\t\t\t\t\t<title>Everyday Italian</title>\n" +
                "\t\t\t\t\t\t</book>\n" +
                "\t\t\t\t  </action>";

        SearchBookRequest searchBookRequest;
        StringReader stringReader = new StringReader(xmlRequest);
        JAXBContext jaxbContext = JAXBContext.newInstance(SearchBookRequest.class);
        XMLInputFactory xif = XMLInputFactory.newInstance();
        XMLStreamReader xsr = xif.createXMLStreamReader(stringReader);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        searchBookRequest = (SearchBookRequest) unmarshaller.unmarshal(xsr);

        System.out.println("name: " + searchBookRequest.getName());
        System.out.println("title: " + searchBookRequest.getBook().getTitle());
    }

    @Test
    public void searchBookResponse() throws JAXBException
    {
        BookResponse bookResponse = new BookResponse();
        bookResponse.setId("2c5be756-60f7-4d48-af99-e43df42e3edb");
        bookResponse.setIsbn("978-2-1234-5680-3");
        bookResponse.setTitle("Everyday Italian");
        bookResponse.setAuthor("Giada De Laurentiis");
        bookResponse.setYear(2005);
        bookResponse.setPrice(30.0);
        bookResponse.setWeight(200);

        BookResponse bookResponse1 = new BookResponse();
        bookResponse1.setId("2c5be756-60f7-4d48-af99-e43df42e3edb");
        bookResponse1.setIsbn("978-2-1234-5680-3");
        bookResponse1.setTitle("Everyday Italian");
        bookResponse1.setAuthor("Giada De Laurentiis");
        bookResponse1.setYear(2005);
        bookResponse1.setPrice(30.0);
        bookResponse1.setWeight(200);

        SearchBookResponse searchBookResponse = new SearchBookResponse();
        searchBookResponse.setName("SearchBooks");
        List<BookResponse> bookResponses = new ArrayList<>();
        bookResponses.add(bookResponse);
        bookResponses.add(bookResponse1);
        searchBookResponse.setBook(bookResponses);

        JAXBContext jaxbContext = JAXBContext.newInstance(SearchBookResponse.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        StringWriter sw = new StringWriter();
        jaxbMarshaller.marshal(searchBookResponse, sw);
        String xmlString = sw.toString();
        System.out.println(xmlString);
    }
}