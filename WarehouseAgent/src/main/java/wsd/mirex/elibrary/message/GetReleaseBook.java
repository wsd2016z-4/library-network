package wsd.mirex.elibrary.message;

import org.w3c.dom.Element;

/** Deserializacja XML-a, ksiazka ktora ma zostac wydana */
public class GetReleaseBook implements XmlDeserializable<GetReleaseBook>
{
    private String id;

    @Override
    public GetReleaseBook deserialize(Element getReleaseBookElement) throws Exception
    {
        GetReleaseBook getReleaseBook = null;

        if (getReleaseBookElement.getAttribute("name").equals("getBook"))
        {
            getReleaseBook = new GetReleaseBook();
            getReleaseBook.setId(getReleaseBookElement.getElementsByTagName("id").item(0).getTextContent());
        }

        return getReleaseBook;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
