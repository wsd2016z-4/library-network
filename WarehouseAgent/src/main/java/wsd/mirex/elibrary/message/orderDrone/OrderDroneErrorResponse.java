package wsd.mirex.elibrary.message.orderDrone;

import wsd.mirex.elibrary.message.AbstractAction;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by pj on 22.01.17.
 */
@XmlRootElement(name = "action")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderDroneErrorResponse extends AbstractAction
{
    @XmlElement(name = "answer")
    private AnswerErrorResponse answer;

    public AnswerErrorResponse getAnswer() {
        return answer;
    }

    public void setAnswer(AnswerErrorResponse answer) {
        this.answer = answer;
    }
}
