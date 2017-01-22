package com.wsd.library.message;

public class MessageCreator {
	public String FormAvailableResponse(String id, int waiting, int time) {
		String content = "<action name=\"checkAvailability\"><answer agree=\"true\">" +
						"<id>" + id + "</id>" +
						"<waiting>" + waiting + "</waiting>" +
						"<time>" + time + "</time>" +
						"</answer></action>";
		return content;
	}

	public String FormNotAvailableResponse(String reason) {
		String content = "<action name=\"checkAvailability\"><answer agree=\"false\">" +
						reason + "</answer></action>";
		return content;
	}
}
