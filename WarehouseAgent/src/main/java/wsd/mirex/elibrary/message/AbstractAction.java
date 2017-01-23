package wsd.mirex.elibrary.message;

import javax.xml.bind.annotation.*;

/**
 * Created by pj on 18.12.16.
 */

@XmlAccessorType(XmlAccessType.FIELD)
public abstract class AbstractAction
{
    @XmlAttribute(name = "name")
    protected String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}