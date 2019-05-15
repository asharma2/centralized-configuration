package org.gomiles.rnd.mongodb.dto;

import org.springframework.data.geo.Point;

public class QPoint {

	protected Position position;

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Point getPoint() {
		return new Point(position.getLat(), position.getLon());
	}

}
