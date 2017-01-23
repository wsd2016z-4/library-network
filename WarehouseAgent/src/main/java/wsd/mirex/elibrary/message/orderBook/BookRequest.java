package wsd.mirex.elibrary.message.orderBook;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * Created by pj on 21.01.17.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class BookRequest
{
    @XmlElement(name = "title")
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}