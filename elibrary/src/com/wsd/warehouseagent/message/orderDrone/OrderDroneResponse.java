package com.wsd.warehouseagent.message.orderDrone;

import com.wsd.warehouseagent.message.AbstractAction;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by pj on 21.01.17.
 */
@XmlRootElement(name = "action")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderDroneResponse extends AbstractAction
{
    @XmlElement(name = "answer")
    private AnswerResponse answer;

    public AnswerResponse getAnswer() {
        return answer;
    }

    public void setAnswer(AnswerResponse answer) {
        this.answer = answer;
    }
}