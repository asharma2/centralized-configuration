package org.gomiles.rnd.influxdb.converter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.influxdb.dto.Point;
import org.influxdb.dto.QueryResult;

public interface PointConverter<P> {

	List<Point> convert(P point);

	List<P> convert(QueryResult queryResult);

	default String convertObjectToString(Object object) {
		return String.valueOf(object);
	}

	default Integer convertDoubleToInt(Object object) {
		return ((Double) object).intValue();
	}

	default Long convertDoubleToLong(Object object) {
		return ((Double) object).longValue();
	}

	default Float convertDoubleToFloat(Object object) {
		return ((Double) object).floatValue();
	}

	default Integer convertFloatToInt(Float object) {
		return ((Float) object).intValue();
	}

	default Long convertFloatToLong(Float object) {
		return ((Float) object).longValue();
	}

	default Double convertFloatToDouble(Float object) {
		return ((Float) object).doubleValue();
	}

	default Date convertStringToDate(Object object) {
		String d = (String) object;
		Date date = Date.from(
				LocalDateTime.parse(d, DateTimeFormatter.ISO_DATE_TIME).atZone(ZoneId.systemDefault()).toInstant());
		return date;
	}

}
