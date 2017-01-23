package com.wsd.warehouseagent.behaviour;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import com.wsd.warehouseagent.message.ContentParser;

/** Behaviour nasluchujacy zdarzen oraz wywolujacy inne Behavioury - dziala caly czas */
public class AwaitClientServer extends CyclicBehaviour
{
    @Override
    public void action()
    {
        ACLMessage msg = myAgent.receive();

        if(msg != null)
        {
            System.out.println("Message from Agent: " + msg);

            String msgContent = msg.getContent();
            ContentParser contentParser = new ContentParser(msgContent);
            String actionType = contentParser.getActionType();

            switch (actionType)
            {
                case BehaviourTypes.RETURN_BOOK_BEHAVIOUR:
                    myAgent.addBehaviour(new ReturnBookBehaviour(msg));
                    break;
                case BehaviourTypes.CHECK_AVAILABILITY_BEHAVIOUR:
                    myAgent.addBehaviour(new CheckAvailabilityBehaviour(msg));
                    break;
                case BehaviourTypes.ISSUE_BOOK_BEHAVIOUR:
                    myAgent.addBehaviour(new IssueBookBehaviour(msg));
                    break;
                case BehaviourTypes.ORDER_BOOK_BEHAVIOUR:
                    myAgent.addBehaviour(new OrderBookBehaviour(msg));
                    break;
                case BehaviourTypes.ORDER_DRONE_BEHAVIOUR:
                    myAgent.addBehaviour(new OrderDroneBehaviour(msg));
                    break;
                case BehaviourTypes.SEARCH_BOOK_BEHAVIOUR:
                    myAgent.addBehaviour(new SearchBookBehaviour(msg));
                    break;
                default:
                    System.out.println("No action type.");
                    break;
            }
        }
        else
        {
            block();
        }
    }
}