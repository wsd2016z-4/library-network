package wsd.mirex.elibrary.message.checkAvailability;

import wsd.mirex.elibrary.message.AbstractAction;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by pj on 21.01.17.
 */
@XmlRootElement(name = "action")
@XmlAccessorType(XmlAccessType.FIELD)
public class CheckAvailabilityRequest extends AbstractAction
{
    @XmlElement(name = "book")
    private BookRequest book;
    @XmlElement(name = "from")
    private FromRequest from;
    @XmlElement(name = "to")
    private ToRequest to;

    public BookRequest getBook() {
        return book;
    }

    public void setBook(BookRequest book) {
        this.book = book;
    }

    public FromRequest getFrom() {
        return from;
    }

    public void setFrom(FromRequest from) {
        this.from = from;
    }

    public ToRequest getTo() {
        return to;
    }

    public void setTo(ToRequest to) {
        this.to = to;
    }
}
