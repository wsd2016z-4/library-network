package wsd.mirex.elibrary.message.orderDrone;

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
    @XmlElement(name = "weight")
    private String weight;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getWeight()
    {
        return weight;
    }

    public void setWeight(String weight)
    {
        this.weight = weight;
    }
}