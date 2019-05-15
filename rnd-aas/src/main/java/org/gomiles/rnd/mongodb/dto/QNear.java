package org.gomiles.rnd.mongodb.dto;

import org.springframework.data.geo.Metrics;
import org.springframework.data.mongodb.core.query.NearQuery;

public class QNear {

	protected Position position;
	protected int results;
	protected double minDistance;
	protected double maxDistance;

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public int getResults() {
		return results;
	}

	public void setResults(int results) {
		this.results = results;
	}

	public double getMinDistance() {
		return minDistance;
	}

	public void setMinDistance(double minDistance) {
		this.minDistance = minDistance;
	}

	public double getMaxDistance() {
		return maxDistance;
	}

	public void setMaxDistance(double maxDistance) {
		this.maxDistance = maxDistance;
	}

	public NearQuery getNearQuery() {
		return NearQuery.near(position.getLat(), position.getLon(), Metrics.KILOMETERS).num(results)
				.minDistance(minDistance).maxDistance(maxDistance);
	}
}
