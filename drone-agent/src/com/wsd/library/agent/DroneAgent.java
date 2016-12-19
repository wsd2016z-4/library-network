package com.wsd.library.agent;

import com.wsd.library.behaviours.DroneAgentServiceBehaviour;

import jade.core.Agent;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.util.Logger;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.DFService;
import jade.domain.FIPAException;


public class DroneAgent extends Agent {
	private Logger mLogger = Logger.getMyLogger(getClass().getName());

	/* 0% - 100% battery status, so 0.0f-1.0f range */
	private float mBatteryStatus = 1.0f;

	/* Current localization */
	private double mLatitude;
	private double mLongitude;

	public float getBatteryStatus() {
		return mBatteryStatus;
	}

	public double getCurrentLatitude() {
		return mLatitude;
	}

	public double getCurrentLongitude() {
		return mLongitude;
	}

	protected void setup() {
		mLogger.log(Logger.INFO, "Drone " + getLocalName() + " initializing.");

		// Registration with the DF
		DFAgentDescription dfd = new DFAgentDescription();
		ServiceDescription sd = new ServiceDescription();
		sd.setType("DroneAgent");
		sd.setName(getName());
		sd.setOwnership("lk");
		dfd.setName(getAID());
		dfd.addServices(sd);

		try {
			DFService.register(this, dfd);
			addBehaviour(new DroneAgentServiceBehaviour(this));
		} catch (FIPAException e) {
			mLogger.log(Logger.SEVERE, "Agent " + getLocalName() + " - Cannot register with DF", e);
			doDelete();
		}

		mLogger.log(Logger.INFO, "Drone " + getLocalName() + " reporting for duty.");
	}
}
