package wsd.mirex.elibrary.message.issueBook;

import wsd.mirex.elibrary.message.AbstractAction;

import javax.xml.bind.annotation.*;

/**
 * Created by pj on 22.01.17.
 */
@XmlRootElement(name = "action")
@XmlAccessorType(XmlAccessType.FIELD)
public class IssueBookResponse extends AbstractAction
{
    @XmlElement(name = "book")
    private BookResponse book;

    public BookResponse getBook() {
        return book;
    }

    public void setBook(BookResponse book) {
        this.book = book;
    }
}