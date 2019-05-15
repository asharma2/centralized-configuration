package org.gomiles.rnd.influxdb;

import org.gomiles.rnd.influxdb.properties.InfluxDBProperties;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;

public class InfluxDBConnectionFactory {

	private InfluxDB connection;
	private InfluxDBProperties properties;

	public InfluxDBConnectionFactory(final InfluxDBProperties properties) {
		this.properties = properties;
	}

	public void start() {
		connection = InfluxDBFactory.connect(properties.getUrl(), properties.getUsername(), properties.getPassword());
		connection.setDatabase(properties.getDatabase());
		connection.setRetentionPolicy(properties.getRetentionPolicy());
		if (properties.isGzip())
			connection.enableGzip();
	}

	public void close() {
		connection.close();
	}

	public InfluxDBProperties getProperties() {
		return properties;
	}

	public InfluxDB getConnection() {
		return connection;
	}

}
