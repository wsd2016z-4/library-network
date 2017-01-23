package wsd.mirex.elibrary.message.orderBook;

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
public class OrderBookErrorResponse extends AbstractAction
{
    @XmlElement(name = "book")
    private BookErrorResponse book;

    public BookErrorResponse getBook() {
        return book;
    }

    public void setBook(BookErrorResponse book) {
        this.book = book;
    }
}