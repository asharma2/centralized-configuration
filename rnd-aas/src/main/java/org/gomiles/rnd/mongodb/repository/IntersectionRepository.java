package org.gomiles.rnd.mongodb.repository;

import java.util.List;

import org.gomiles.rnd.mongodb.dto.QPolygon;

public interface IntersectionRepository<P> {

	List<P> findIntersectingResources(QPolygon query);
}
