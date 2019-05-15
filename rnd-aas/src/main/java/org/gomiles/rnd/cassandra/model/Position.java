package org.gomiles.rnd.cassandra.model;

import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import com.datastax.driver.core.DataType;

@UserDefinedType("position")
public class Position {

	@CassandraType(type = DataType.Name.DOUBLE)
	protected double lat;
	@CassandraType(type = DataType.Name.DOUBLE)
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
