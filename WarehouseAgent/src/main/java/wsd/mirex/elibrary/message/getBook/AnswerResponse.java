package wsd.mirex.elibrary.message.getBook;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

/**
 * Created by pj on 22.01.17.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class AnswerResponse
{
    @XmlAttribute(name = "agree")
    private String agree;
    @XmlValue
    private String content;

    public String getAgree() {
        return agree;
    }

    public void setAgree(String agree) {
        this.agree = agree;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
