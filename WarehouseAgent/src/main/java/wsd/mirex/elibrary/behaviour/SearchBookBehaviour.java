package wsd.mirex.elibrary.behaviour;

import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import wsd.mirex.elibrary.dao.BookDao;
import wsd.mirex.elibrary.message.searchBook.BookResponse;
import wsd.mirex.elibrary.message.searchBook.SearchBookRequest;
import wsd.mirex.elibrary.message.searchBook.SearchBookResponse;
import wsd.mirex.elibrary.model.Book;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pj on 23.01.17.
 */
public class SearchBookBehaviour extends OneShotBehaviour
{
    private String xmlRequest;
    private ACLMessage msg;
    private SearchBookRequest searchBookRequest;
    private BookDao bookDao;
    private List<Book> books;

    public SearchBookBehaviour(ACLMessage msg)
    {
        xmlRequest = msg.getContent();
        this.msg = msg;
        bookDao = new BookDao();
        books = new ArrayList<>();
    }

    public void deserializeXmlToObject()
    {
        try
        {
            StringReader stringReader = new StringReader(xmlRequest);
            JAXBContext jaxbContext = JAXBContext.newInstance(SearchBookRequest.class);
            XMLInputFactory xif = XMLInputFactory.newInstance();
            XMLStreamReader xsr = xif.createXMLStreamReader(stringReader);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            searchBookRequest = (SearchBookRequest) unmarshaller.unmarshal(xsr);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public String serializeObjectToXml()
    {
        try
        {
            SearchBookResponse searchBookResponse = new SearchBookResponse();
            searchBookResponse.setName("SearchBooks");
            List<BookResponse> bookResponses = new ArrayList<>();

            for (int i = 0; i < books.size(); ++i)
            {
                BookResponse bookResponse = new BookResponse();
                bookResponse.setId(books.get(i).getIdBook());
                bookResponse.setIsbn(books.get(i).getIsbn());
                bookResponse.setTitle(books.get(i).getTitle());
                bookResponse.setAuthor(books.get(i).getAuthor());
                bookResponse.setYear(books.get(i).getYear());
                bookResponse.setPrice(books.get(i).getPrice());
                bookResponse.setWeight(books.get(i).getWeight());
                bookResponses.add(bookResponse);
                searchBookResponse.setBook(bookResponses);
            }
            searchBookResponse.setBook(bookResponses);
            JAXBContext jaxbContext = JAXBContext.newInstance(SearchBookResponse.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(searchBookResponse, sw);
            String xmlString = sw.toString();

            return xmlString;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public void findBooks()
    {
        books = bookDao.findByTitle(searchBookRequest.getBook().getTitle());
    }

    @Override
    public void action()
    {
        deserializeXmlToObject();
        findBooks();
        serializeObjectToXml();

        ACLMessage reply = new ACLMessage( ACLMessage.CONFIRM );
        reply.setContent(serializeObjectToXml());
        reply.setSender(myAgent.getAID());
        reply.addReceiver( msg.getSender() );
        myAgent.send(reply);
    }
}
