package wsd.mirex.elibrary.message.searchBook;

import wsd.mirex.elibrary.message.AbstractAction;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by pj on 21.01.17.
 */
@XmlRootElement(name = "action")
@XmlAccessorType(XmlAccessType.FIELD)
public class SearchBookResponse extends AbstractAction
{
    @XmlElement(name = "book")
    List<BookResponse> book;

    public List<BookResponse> getBook() {
        return book;
    }

    public void setBook(List<BookResponse> book) {
        this.book = book;
    }
}