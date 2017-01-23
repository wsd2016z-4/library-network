package com.wsd.droneagent.message;

public class MessageCreator {
	public String FormAvailableResponse(int waiting, int time) {
		String content = "<action name=\"checkAvailability\"><answer agree=\"true\">" +
						"<waiting>" + waiting + "</waiting>" +
						"<time>" + time + "</time>" +
						"</answer></action>";
		return content;
	}

	public String FormNotAvailableResponse(String reason) {
		String content = "<action name=\"checkAvailability\"><answer agree=\"false\">" +
						reason +
						"</answer></action>";
		return content;
	}

	public String FormOrderAcceptedResponse(int travelTime) {
		String content = "<action name=\"orderDrone\"><answer agree=\"true\">" +
						"<time>" + travelTime + "</time>" +
						"</answer></action>";
		return content;
	}

	public String FormOrderDeniedResponse(String reason) {
		String content = "<action name=\"checkAvailability\"><answer agree=\"false\">" +
						reason +
						"</answer></action>";
		return content;
	}
}
