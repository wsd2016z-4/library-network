package wsd.mirex.elibrary.message.orderDrone;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * Created by pj on 22.01.17.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class FromRequest
{
    @XmlElement(name = "id")
    private String id;
    @XmlElement(name = "latitude")
    private Double latitude;
    @XmlElement(name = "longitude")
    private Double longitude;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
