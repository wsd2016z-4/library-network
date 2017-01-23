package wsd.mirex.elibrary.message.checkAvailability;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * Created by pj on 21.01.17.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class BookRequest
{
    @XmlElement(name = "id")
    private String id;
    @XmlElement(name = "weight")
    private Integer weight;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}