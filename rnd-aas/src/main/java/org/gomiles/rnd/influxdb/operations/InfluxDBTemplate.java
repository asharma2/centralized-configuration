package org.gomiles.rnd.influxdb.operations;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import org.gomiles.rnd.influxdb.InfluxDBConnectionFactory;
import org.gomiles.rnd.influxdb.converter.PointConverter;
import org.influxdb.InfluxDB;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Pong;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;

public class InfluxDBTemplate<P> implements InfluxDBOperations<P> {

	PointConverter<P> pointConverter;
	InfluxDBConnectionFactory connectionFactory;

	public InfluxDBTemplate(PointConverter<P> pointConverter, InfluxDBConnectionFactory connectionFactory) {
		this.pointConverter = pointConverter;
		this.connectionFactory = connectionFactory;
	}

	@Override
	public void createDatabase() {
		String database = getConnectionFactory().getProperties().getDatabase();
		connectionFactory.getConnection().query(new Query(String.format("CREATE DATABASE %s", database)));
	}

	@Override
	public void write(P point) {
		write(Arrays.asList(point));
	}

	@Override
	public void write(Collection<P> points) {
		String database = getConnectionFactory().getProperties().getDatabase();
		String retentionPolicy = getConnectionFactory().getProperties().getRetentionPolicy();

		final BatchPoints ops = BatchPoints.database(database).retentionPolicy(retentionPolicy)
				.consistency(InfluxDB.ConsistencyLevel.ALL).build();
		points.forEach(t -> pointConverter.convert(t).forEach(ops::point));
		getConnection().write(ops);
	}

	@Override
	public QueryResult query(Query query) {
		return getConnection().query(query);
	}

	@Override
	public QueryResult query(Query query, TimeUnit timeUnit) {
		return getConnection().query(query, timeUnit);
	}

	@Override
	public void query(Query query, int chunkSize, Consumer<QueryResult> consumer) {
		getConnection().query(query, chunkSize, consumer);
	}

	@Override
	public Pong ping() {
		return getConnection().ping();
	}

	@Override
	public String version() {
		return getConnection().version();
	}

	protected InfluxDBConnectionFactory getConnectionFactory() {
		return connectionFactory;
	}

	protected InfluxDB getConnection() {
		return connectionFactory.getConnection();
	}

	@Override
	public List<P> queryForList(Query query) {
		return pointConverter.convert(getConnection().query(query));
	}

}
