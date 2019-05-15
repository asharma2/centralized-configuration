package org.gomiles.rnd.mongodb.repository;

import java.util.List;

import org.gomiles.rnd.mongodb.dto.QNear;
import org.gomiles.rnd.mongodb.dto.QPoint;

public interface NearRepository<R> {

	List<R> near(QNear qNear);

	List<R> nearPoint(QPoint qPoint, double minDistance, double maxDistance);

	List<R> nearSphere(QPoint qPoint, double minDistance, double maxDistance);
}
