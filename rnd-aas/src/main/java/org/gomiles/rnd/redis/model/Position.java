package org.gomiles.rnd.redis.model;

import java.io.Serializable;

public class Position implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	double lat;
	double lon;

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

}
