package org.gomiles.rnd.influxdb.converter;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.gomiles.rnd.influxdb.model.MemoryPoint;
import org.influxdb.dto.Point;
import org.influxdb.dto.QueryResult;
import org.influxdb.dto.QueryResult.Result;
import org.influxdb.dto.QueryResult.Series;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MemoryPointConverter implements PointConverter<MemoryPoint> {

	private static final Logger LOG = LoggerFactory.getLogger(MemoryPointConverter.class);

	@Override
	public List<Point> convert(MemoryPoint memoryPoint) {
		Point point = Point.measurement("memory").time(memoryPoint.getTime().getTime(), TimeUnit.MILLISECONDS)
				.addField("name", memoryPoint.getName()).addField("free", memoryPoint.getFree())
				.addField("used", memoryPoint.getUsed()).addField("buffer", memoryPoint.getBuffer()).build();

		// Point p =
		// Point.measurementByPOJO(MemoryPoint.class).addFieldsFromPOJO(memoryPoint).build();
		LOG.info("Point: " + point);
		return Arrays.asList(point);
	}

	@Override
	public List<MemoryPoint> convert(QueryResult queryResult) {
		List<Result> results = queryResult.getResults();
		List<MemoryPoint> points = new LinkedList<>();
		for (Result result : results) {
			List<Series> serieses = result.getSeries();
			for (Series series : serieses) {
				List<String> columns = series.getColumns();
				Map<String, Integer> mapping = IntStream.range(0, columns.size()).boxed()
						.collect(Collectors.toMap(columns::get, Function.identity()));
				for (List<Object> values : series.getValues()) {
					MemoryPoint mp = new MemoryPoint();
					mp.setBuffer(convertDoubleToInt(values.get(mapping.get("buffer"))));
					mp.setFree(convertDoubleToInt(values.get(mapping.get("free"))));
					mp.setUsed(convertDoubleToInt(values.get(mapping.get("used"))));
					mp.setName(convertObjectToString(values.get(mapping.get("name"))));
					mp.setTime(convertStringToDate(values.get(mapping.get("time"))));
					points.add(mp);
				}
			}
		}
		return points;
	}

}
