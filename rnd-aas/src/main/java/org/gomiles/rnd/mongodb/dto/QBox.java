package org.gomiles.rnd.mongodb.dto;

import org.springframework.data.geo.Box;
import org.springframework.data.geo.Point;

public class QBox {

	protected Position first;
	protected Position second;

	public Position getFirst() {
		return first;
	}

	public void setFirst(Position first) {
		this.first = first;
	}

	public Position getSecond() {
		return second;
	}

	public void setSecond(Position second) {
		this.second = second;
	}

	public Box getBox() {
		return new Box(new Point(first.getLat(), first.getLon()), new Point(second.getLat(), second.getLon()));
	}

}
