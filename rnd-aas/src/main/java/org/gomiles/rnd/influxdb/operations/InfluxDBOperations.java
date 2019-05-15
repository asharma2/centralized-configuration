package org.gomiles.rnd.influxdb.operations;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import org.influxdb.dto.Pong;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;

public interface InfluxDBOperations<P> {

	void createDatabase();

	void write(P point);

	void write(Collection<P> points);

	List<P> queryForList(final Query query);

	QueryResult query(final Query query);

	QueryResult query(final Query query, final TimeUnit timeUnit);

	void query(final Query query, final int chunkSize, final Consumer<QueryResult> consumer);

	Pong ping();

	String version();
}
