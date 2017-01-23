package com.wsd.warehouseagent.message.returnBookByDron;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

/**
 * Created by pj on 22.01.17.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class BookResponse
{
    @XmlAttribute
    private String status;
    @XmlValue
    private String content;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
