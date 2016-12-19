package wsd.mirex.elibrary.message;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;

/** Deserializacja XML-a, wyciaganie najwazniejszych informacji */
public class ContentParser
{
    private Document document;

    public ContentParser(String content)
    {
        try
        {
            document = parseToXml(content);
        }
        catch (Exception e)
        {
            System.err.println("ContentParserError: Can not parse String to Document.\n"
                    + "System message: " + e.getMessage());
        }
    }

    public Document parseToXml(String contentToParse) throws Exception
    {
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
                .parse(new InputSource(new StringReader(contentToParse.trim())));

        return doc;
    }

    public Element getRootElement()
    {
        if(null != document)
            return document.getDocumentElement();

        return null;
    }

    public String getActionType()
    {
        Element actionElement = getRootElement();

        if(null != actionElement && actionElement.getNodeName().equals("action"))
            return actionElement.getAttribute("name");

        return null;
    }
}