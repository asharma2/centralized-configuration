package org.gomiles.rnd.mongodb.repository;

import java.util.List;

import org.gomiles.rnd.mongodb.dto.QBox;
import org.gomiles.rnd.mongodb.dto.QCircle;
import org.gomiles.rnd.mongodb.dto.QPolygon;
import org.gomiles.rnd.mongodb.dto.QSphere;

public interface WithinRepository<R> {

	List<R> within(QCircle qCircle);

	List<R> within(QBox qBox);

	List<R> within(QPolygon qPolygon);

	List<R> within(QSphere qSphere);

}
