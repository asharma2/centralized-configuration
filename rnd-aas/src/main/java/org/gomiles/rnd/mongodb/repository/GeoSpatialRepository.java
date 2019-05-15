package org.gomiles.rnd.mongodb.repository;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.LinkedList;
import java.util.List;

import org.bson.Document;
import org.gomiles.rnd.mongodb.dto.QBox;
import org.gomiles.rnd.mongodb.dto.QCircle;
import org.gomiles.rnd.mongodb.dto.QNear;
import org.gomiles.rnd.mongodb.dto.QPoint;
import org.gomiles.rnd.mongodb.dto.QPolygon;
import org.gomiles.rnd.mongodb.dto.QSphere;
import org.gomiles.rnd.mongodb.model.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.GeoResult;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class GeoSpatialRepository
		implements IntersectionRepository<Restaurant>, WithinRepository<Restaurant>, NearRepository<Restaurant> {

	@Autowired
	MongoOperations mongoOperations;

	@Override
	public List<Restaurant> findIntersectingResources(QPolygon query) {
		BasicQuery bQuery = new BasicQuery(new Document("position", new Document("$geoIntersects",
				new Document("$geometry", new GeoJsonPolygon(query.getPolygon().getPoints())))));
		return mongoOperations.find(bQuery, Restaurant.class);
	}

	@Override
	public List<Restaurant> within(QCircle qCircle) {
		BasicQuery bQuery = new BasicQuery(
				new Document("position", new Document("$geoWithin", new Document("$circle", qCircle.getCircle()))));
		return mongoOperations.find(bQuery, Restaurant.class);
	}

	@Override
	public List<Restaurant> within(QBox qBox) {
		BasicQuery bQuery = new BasicQuery(
				new Document("position", new Document("$geoWithin", new Document("$box", qBox.getBox()))));
		return mongoOperations.find(bQuery, Restaurant.class);
	}

	@Override
	public List<Restaurant> within(QPolygon qPolygon) {
		BasicQuery bQuery = new BasicQuery(
				new Document("position", new Document("$geoWithin", new Document("$polygon", qPolygon.getPolygon()))));
		return mongoOperations.find(bQuery, Restaurant.class);
	}

	@Override
	public List<Restaurant> within(QSphere qSphere) {
		BasicQuery bQuery = new BasicQuery(new Document("position",
				new Document("$geoWithin", new Document("$centerSphere", qSphere.getSphere()))));
		return mongoOperations.find(bQuery, Restaurant.class);
	}

	@Override
	public List<Restaurant> near(QNear qNear) {
		GeoResults<Restaurant> results = mongoOperations.geoNear(qNear.getNearQuery(), Restaurant.class);
		List<Restaurant> restaurants = new LinkedList<>();
		for (GeoResult<Restaurant> result : results) {
			restaurants.add(result.getContent());
		}
		return restaurants;
	}

	@Override
	public List<Restaurant> nearSphere(QPoint qPoint, double minDistance, double maxDistance) {
		Query query = query(
				where("position").nearSphere(qPoint.getPoint()).minDistance(minDistance).maxDistance(maxDistance));
		return mongoOperations.find(query, Restaurant.class);
	}

	@Override
	public List<Restaurant> nearPoint(QPoint qPoint, double minDistance, double maxDistance) {
		Query query = query(
				where("position").near(qPoint.getPoint()).minDistance(minDistance).maxDistance(maxDistance));
		return mongoOperations.find(query, Restaurant.class);
	}

}
