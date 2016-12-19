package wsd.mirex.elibrary.message;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.Objects;

/** Serializacja XML-a, decyzja o wydaniu ksiazki */
public class SendReleaseBook extends SendAbstract implements XmlSerializable
{
    private Boolean agree;

    @Override
    public String serialize() throws Exception
    {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.newDocument();

        Element actionElement = doc.createElement("action");
        actionElement.setAttribute("name", "getBook");
        doc.appendChild(actionElement);

        Element bookElement = doc.createElement("answer");
        bookElement.setAttribute("agree", Objects.toString(getAgree()));
        actionElement.appendChild(bookElement);

        return documentToString(doc);
    }

    public Boolean getAgree() {
        return agree;
    }

    public void setAgree(Boolean agree) {
        this.agree = agree;
    }
}