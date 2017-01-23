package com.wsd.warehouseagent.message.getBook;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * Created by pj on 22.01.17.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class BookRequest
{
    @XmlElement(name = "id")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
