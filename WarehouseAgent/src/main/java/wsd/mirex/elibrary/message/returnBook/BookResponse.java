package wsd.mirex.elibrary.message.returnBook;

import javax.xml.bind.annotation.*;

/**
 * Created by pj on 21.01.17.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class BookResponse
{
    @XmlAttribute(name = "status")
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
