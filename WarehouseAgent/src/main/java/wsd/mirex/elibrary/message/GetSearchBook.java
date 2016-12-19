package wsd.mirex.elibrary.message;

import org.w3c.dom.Element;

/** Deserializacja XMLa, odczytuje informacje dotyczaca tytulu ksiazki, ktora ma zostac wyszukana */
public class GetSearchBook implements XmlDeserializable<GetSearchBook>
{
    private String title;

    @Override
    public GetSearchBook deserialize(Element getSearchBookElement) throws Exception
    {
        GetSearchBook getSearchBook = null;

        if(getSearchBookElement.getAttribute("name").equals("searchBooks"))
        {
            getSearchBook = new GetSearchBook();
            getSearchBook.setTitle(getSearchBookElement.getElementsByTagName("title").item(0).getTextContent());
        }
        return getSearchBook;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }
}