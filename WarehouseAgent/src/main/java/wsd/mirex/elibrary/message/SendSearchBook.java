package wsd.mirex.elibrary.message;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.Objects;

/** Serializacja XMLa, zawiera informacje dotyczace znalezionej ksiazki */
public class SendSearchBook extends SendAbstract implements XmlSerializable
{
    private String status;
    private String id;
    private String isbn;
    private String title;
    private String author;
    private Integer year;
    private Double price;
    private Integer weight;

    @Override
    public String serialize() throws Exception
    {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.newDocument();

        if(status.equals("available"))
        {
            Element actionElement = doc.createElement("action");
            actionElement.setAttribute("name", "searchBooks");
            doc.appendChild(actionElement);

            Element bookElement = doc.createElement("book");
            bookElement.setAttribute("status", getStatus());
            actionElement.appendChild(bookElement);

            Element idElement = doc.createElement("id");
            idElement.setTextContent(getId());
            bookElement.appendChild(idElement);

            Element isbnElement = doc.createElement("isbn");
            isbnElement.setTextContent(getIsbn());
            bookElement.appendChild(isbnElement);

            Element titleElement = doc.createElement("title");
            titleElement.setTextContent(getTitle());
            bookElement.appendChild(titleElement);

            Element authorElement = doc.createElement("author");
            authorElement.setTextContent(getAuthor());
            bookElement.appendChild(authorElement);

            Element yearElement = doc.createElement("year");
            yearElement.setTextContent(Objects.toString(getYear()));
            bookElement.appendChild(yearElement);

            Element priceElement = doc.createElement("price");
            priceElement.setTextContent(Objects.toString(getPrice()));
            bookElement.appendChild(priceElement);

            Element weightElement = doc.createElement("weight");
            weightElement.setTextContent(Objects.toString(getWeight()));
            bookElement.appendChild(weightElement);
        }
        else
        {
            Element actionElement = doc.createElement("action");
            actionElement.setAttribute("name", "searchBooks");
            doc.appendChild(actionElement);

            Element bookElement = doc.createElement("book");
            bookElement.setAttribute("status", getStatus());
            actionElement.appendChild(bookElement);
        }

        return documentToString(doc);
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getIsbn()
    {
        return isbn;
    }

    public void setIsbn(String isbn)
    {
        this.isbn = isbn;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getAuthor()
    {
        return author;
    }

    public void setAuthor(String author)
    {
        this.author = author;
    }

    public Integer getYear()
    {
        return year;
    }

    public void setYear(Integer year)
    {
        this.year = year;
    }

    public Double getPrice()
    {
        return price;
    }

    public void setPrice(Double price)
    {
        this.price = price;
    }

    public Integer getWeight()
    {
        return weight;
    }

    public void setWeight(Integer weight)
    {
        this.weight = weight;
    }
}