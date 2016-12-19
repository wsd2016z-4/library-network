package com.wsd.library.message;

public class MessageCreator {
	public String FormAvailabilityResponse(String resp) {
		String content = "<action name=\"checkAvailability\"><answer agree=\"false\">" + resp + "</answer></action>";
		return content;
	}
}
