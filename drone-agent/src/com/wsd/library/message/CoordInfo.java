package com.wsd.library.message;

public class CoordInfo {
	public double latitude;
	public double longitude;

	public CoordInfo()
	{
		latitude = 0.0;
		longitude = 0.0;
	}

	public CoordInfo(double lat, double lon)
	{
		latitude = lat;
		longitude = lon;
	}
}
