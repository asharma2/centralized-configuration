package org.gomiles.rnd.mongodb.dto;

public class Position {

	protected double lat;
	protected double lon;

	public Position() {
	}

	public Position(double lat, double lon) {
		this.lat = lat;
		this.lon = lon;
	}

	public double getLat() {
		return lat;
	}

	public Position setLat(double lat) {
		this.lat = lat;
		return this;
	}

	public double getLon() {
		return lon;
	}

	public Position setLon(double lon) {
		this.lon = lon;
		return this;
	}

}
