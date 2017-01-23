package wsd.mirex.elibrary.message.checkAvailability;

import javax.persistence.criteria.CriteriaBuilder;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * Created by pj on 21.01.17.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class AnswerResponse
{
    @XmlAttribute(name = "agree")
    private String agree;
    @XmlElement(name = "waiting")
    private String waiting;
    @XmlElement(name = "time")
    private Integer time;

    public String getAgree() {
        return agree;
    }

    public void setAgree(String agree) {
        this.agree = agree;
    }

    public String getWaiting() {
        return waiting;
    }

    public void setWaiting(String waiting) {
        this.waiting = waiting;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }
}
