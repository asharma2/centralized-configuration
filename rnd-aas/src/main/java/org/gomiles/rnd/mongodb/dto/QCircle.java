package org.gomiles.rnd.mongodb.dto;

import org.springframework.data.geo.Circle;

public class QCircle {

	protected Position position;
	protected double radius;

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public Circle getCircle() {
		return new Circle(position.getLat(), position.getLon(), radius);
	}

}
