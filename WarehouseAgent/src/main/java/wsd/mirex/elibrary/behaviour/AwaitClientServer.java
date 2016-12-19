package wsd.mirex.elibrary.behaviour;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import wsd.mirex.elibrary.message.ContentParser;

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
                case BehaviourTypes.RELEASE_BOOK_BEHAVIOUR:
                        myAgent.addBehaviour(new ReleaseBookBehaviour(contentParser.getRootElement()));
                    break;
                case BehaviourTypes.RELEASE_REQUEST_BEHAVIOUR:
                        myAgent.addBehaviour(new ReleaseRequestBehaviour());
                    break;
                case BehaviourTypes.RELEASE_STATUS_BEHAVIOUR:
                        myAgent.addBehaviour(new ReleaseStatusBehaviour());
                    break;
                case BehaviourTypes.RETURNED_BOOK_INFO_BEHAVIOUR:
                        myAgent.addBehaviour(new ReturnedBookInfoBehaviour());
                    break;
                case BehaviourTypes.RETURN_LIST_OF_BOOKS_BEHAVIOUR:
                        myAgent.addBehaviour(new ReturnListOfBooksBehaviour());
                    break;
                case BehaviourTypes.RETURN_REQUEST_BEHAVIOUR:
                        myAgent.addBehaviour(new ReturnRequestBehaviour());
                    break;
                case BehaviourTypes.SEARCH_BOOK_REQUEST_BEHAVIOUR:
                        myAgent.addBehaviour(new SearchBookRequestBehaviour(contentParser.getRootElement()));
                    break;
                case BehaviourTypes.TAKE_BOOK_BEHAVIOUR:
                        myAgent.addBehaviour(new TakeBookBehaviour());
                    break;
                default:
                    System.out.println("No action type.");
                    break;
            }
        }
    }
}