package com.wsd.library.agent;

import com.wsd.library.behaviours.DroneAgentServiceBehaviour;

import jade.core.Agent;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.util.Logger;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.DFService;
import jade.domain.FIPAException;
import com.wsd.library.agent.DroneStatus;
import com.wsd.library.message.BookInfo;
import com.wsd.library.message.WarehouseInfo;
import com.wsd.library.message.CoordInfo;


public class DroneAgent extends Agent {
	private Logger mLogger = Logger.getMyLogger(getClass().getName());

	/* 0% - 100% battery status, so 0.0f-1.0f range */
	private float mBatteryStatus = 1.0f;

	/* Max range in km, current range is this * mBatteryStatus */
	private double mMaxRange = 20.0f; // km

	/* Current localization */
	private CoordInfo mCoords;

	/* Current status */
	private DroneStatus mDroneStatus = DroneStatus.INIT;

	public float getBatteryStatus() {
		return mBatteryStatus;
	}

	public double getRange() {
		return mBatteryStatus * mMaxRange;
	}

	public CoordInfo getCurrentLocation() {
		return mCoords;
	}

	// calculates distance between two coordinates (Haversine formula)
	double calculatePath(CoordInfo from, CoordInfo to) {
		double R = 6371; // km
		double dLat = (to.latitude - from.latitude) * Math.PI / 180.0f;
		double dLon = (to.longitude - from.longitude) * Math.PI / 180.0f;
		double fromRad = from.latitude * Math.PI / 180.0f;
		double toRad = to.latitude * Math.PI / 180.0f;

		double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
					Math.sin(dLon/2) * Math.sin(dLon/2) * Math.cos(fromRad) * Math.cos(toRad);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		return R * c;
	}

	// checks if book can be transferred from A to B
	public DroneStatus canTransferBook(BookInfo book, WarehouseInfo from, WarehouseInfo to) {
		if (mDroneStatus != DroneStatus.AVAILABLE)
			return mDroneStatus; // we cannot transfer because of reasons

		// we are available - check if range fits
		double distance = calculatePath(mCoords, from.coords); // from drone to source warehouse
		distance += calculatePath(from.coords, to.coords); // from source to destination warehouse
		mLogger.log(Logger.INFO, "Path to do has " + distance + " km");

		if (distance > getRange())
			return DroneStatus.TOO_FAR;

		return mDroneStatus;
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

		mDroneStatus = DroneStatus.AVAILABLE;

		// set arbitrary drone position here
		mCoords = new CoordInfo(21.201245, 52.201452);

		mLogger.log(Logger.INFO, "Drone " + getLocalName() + " reporting for duty.");
	}
}
