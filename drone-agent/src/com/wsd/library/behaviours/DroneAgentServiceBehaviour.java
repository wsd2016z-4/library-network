package com.wsd.library.behaviours;

import com.wsd.library.agent.DroneAgent;
import com.wsd.library.message.ContentParser;
import com.wsd.library.message.MessageCreator;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.util.Logger;
import com.wsd.library.agent.DroneStatus;
import java.util.UUID;
import com.wsd.library.message.BookInfo;
import com.wsd.library.message.WarehouseInfo;


public class DroneAgentServiceBehaviour extends CyclicBehaviour {
	private DroneAgent mAgentRef = null;
	private Logger mLogger = Logger.getMyLogger(getClass().getName());
	private MessageTemplate mExpectedTemplate = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);

	private UUID mSessionID;

	public DroneAgentServiceBehaviour(DroneAgent a) {
		super(a);
		mAgentRef = a;
	}

	void CheckDroneAvailability(ACLMessage msg, ACLMessage reply) {
		String content = msg.getContent();

		ContentParser parser = null;
		try {
			parser = new ContentParser(content);
		} catch (Exception e) {
			mLogger.log(Logger.SEVERE, "Failed to parse message from sender", e);
			NotUnderstood(msg, reply, "Incorrect message");
			return;
		}

		BookInfo book = parser.getBookInfo();
		WarehouseInfo from = parser.getWarehouseInfo("from");
		WarehouseInfo to = parser.getWarehouseInfo("to");

		if (book == null || from == null || to == null)
		{
			mLogger.log(Logger.SEVERE, "Incomplete message received");
			NotUnderstood(msg, reply, "Incomplete message");
			return;
		}

		mLogger.log(Logger.INFO, "checkAvailability: Received book info id = " + book.id +
								" weight = " + book.weight);
		mLogger.log(Logger.INFO, "checkAvailability: Received source info id = " + from.id +
								" lat = " + from.coords.latitude + " lon = " + from.coords.longitude);
		mLogger.log(Logger.INFO, "checkAvailability: Received target info id = " + to.id +
								" lat = " + to.coords.latitude + " lon = " + to.coords.longitude);

		MessageCreator msgCreator = new MessageCreator();

		reply.setPerformative(ACLMessage.INFORM);
		DroneStatus status = mAgentRef.canTransferBook(book, from, to);
		if (status == DroneStatus.AVAILABLE)
		{
			mSessionID = UUID.randomUUID();
			reply.setContent(msgCreator.FormAvailableResponse(mSessionID.toString(), 10, 10));
		}
		else
		{
			String reason;
			switch (status)
			{
			case INIT: reason = "UNINITIALIZED"; break;
			case IN_TRANSIT: reason = "IN_TRANSIT"; break;
			case CHARGING: reason = "CHARGING"; break;
			case TOO_FAR: reason = "TOO_FAR"; break;
			default: reason = "OTHER"; break;
			}
			reply.setContent(msgCreator.FormNotAvailableResponse(reason));
		}
	}

	void OrderDrone(ACLMessage msg, ACLMessage reply) {
		// TODO must ask DroneAgent to decide whether to accept the order or not and reply accordingly
		MessageCreator msgCreator = new MessageCreator();

		reply.setPerformative(ACLMessage.REFUSE);
		reply.setContent(msgCreator.FormNotAvailableResponse("Not implemented"));
	}

	void NotUnderstood(ACLMessage msg, ACLMessage reply, String act) {
		mLogger.log(Logger.SEVERE, "Not understood behaviour request " + act + " from "
				+ msg.getSender().getLocalName());
		reply.setPerformative(ACLMessage.NOT_UNDERSTOOD);
		reply.setContent("( (Unexpected-act " + act + "))");
	}

	public void action() {
		mLogger.log(Logger.INFO, mAgentRef.getLocalName() + ": Alive, waiting for transfer. Battery at "
				+ Float.toString(mAgentRef.getBatteryStatus()));

		ACLMessage msg = mAgentRef.blockingReceive(mExpectedTemplate);
		ACLMessage reply = msg.createReply();

		if (msg.getPerformative() == ACLMessage.REQUEST) {
			String content = msg.getContent();

			mLogger.log(Logger.INFO, "Received message " + content);

			ContentParser contentParser = null;
			try {
				contentParser = new ContentParser(content);
			} catch (Exception e) {
				mLogger.log(Logger.SEVERE, "Failed to parse message from sender", e);
				NotUnderstood(msg, reply, "Incorrect message");
				mAgentRef.send(reply);
				return;
			}

			String actionType = contentParser.getActionType();

			mLogger.log(Logger.INFO, "Received action type: " + actionType);

			switch (actionType)
			{
			case ActionTypes.CHECK_DRONE_AVAILABILITY:
				CheckDroneAvailability(msg, reply);
				break;
			case ActionTypes.ORDER_DRONE:
				OrderDrone(msg, reply);
				break;
			default:
				NotUnderstood(msg, reply, actionType);
			}
		} else {
			NotUnderstood(msg, reply, ACLMessage.getPerformative(msg.getPerformative()));
		}

		mAgentRef.send(reply);
	}
}
