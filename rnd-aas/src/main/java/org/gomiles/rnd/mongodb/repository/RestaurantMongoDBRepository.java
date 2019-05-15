package org.gomiles.rnd.mongodb.repository;

import java.util.List;

import org.gomiles.rnd.mongodb.model.Restaurant;
import org.springframework.data.geo.Box;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Polygon;
import org.springframework.data.mongodb.core.geo.Sphere;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RestaurantMongoDBRepository extends PagingAndSortingRepository<Restaurant, String> {

	List<Restaurant> findByPositionWithin(Polygon polygon);

	List<Restaurant> findByPositionWithin(Circle circle);

	List<Restaurant> findByPositionWithin(Sphere sphere);

	List<Restaurant> findByPositionWithin(Box box);

}
