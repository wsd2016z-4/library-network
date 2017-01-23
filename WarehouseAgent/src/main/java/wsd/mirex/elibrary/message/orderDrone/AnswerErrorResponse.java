package wsd.mirex.elibrary.message.orderDrone;

import javax.xml.bind.annotation.*;

/**
 * Created by pj on 22.01.17.
 */
@XmlRootElement(name = "action")
@XmlAccessorType(XmlAccessType.FIELD)
public class AnswerErrorResponse
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