package org.gomiles.rnd.mongodb.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.geo.Point;
import org.springframework.data.geo.Polygon;

public class QPolygon {

	protected List<Position> coordinates;

	public List<Position> getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(List<Position> coordinates) {
		this.coordinates = coordinates;
	}

	public Polygon getPolygon() {
		List<Point> points = coordinates.stream().map(c -> new Point(c.getLat(), c.getLon()))
				.collect(Collectors.toList());
		return new Polygon(points);
	}

}
