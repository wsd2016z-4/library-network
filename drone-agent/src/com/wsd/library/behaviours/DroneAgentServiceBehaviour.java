package com.wsd.library.behaviours;

import com.wsd.library.agent.DroneAgent;
import com.wsd.library.message.ContentParser;
import com.wsd.library.message.MessageCreator;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.util.Logger;


public class DroneAgentServiceBehaviour extends CyclicBehaviour {
	private DroneAgent mAgentRef = null;
	private Logger mLogger = Logger.getMyLogger(getClass().getName());
	private MessageTemplate mExpectedTemplate = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);

	public DroneAgentServiceBehaviour(DroneAgent a) {
		super(a);
		mAgentRef = a;
	}

	void CheckDroneAvailability(ACLMessage msg, ACLMessage reply) {
		// TODO must form an XML using ContentParser
		// TODO must ask DroneAgent for status reply
		MessageCreator msgCreator = new MessageCreator();

		reply.setPerformative(ACLMessage.REFUSE);
		reply.setContent(msgCreator.FormAvailabilityResponse("Not yet implemented"));
	}

	void OrderDrone(ACLMessage msg, ACLMessage reply) {
		// TODO must ask DroneAgent to decide whether to accept the order or not and reply accordingly
		MessageCreator msgCreator = new MessageCreator();

		reply.setPerformative(ACLMessage.REFUSE);
		reply.setContent(msgCreator.FormAvailabilityResponse("Not yet implemented"));
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
