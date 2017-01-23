package com.wsd.warehouseagent.message.searchBook;

import com.wsd.warehouseagent.message.AbstractAction;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by pj on 21.01.17.
 */
@XmlRootElement(name = "action")
@XmlAccessorType(XmlAccessType.FIELD)
public class SearchBookRequest extends AbstractAction
{
    @XmlElement(name = "book")
    private BookRequest book;

    public BookRequest getBook()
    {
        return book;
    }

    public void setBook(BookRequest book)
    {
        this.book = book;
    }
}