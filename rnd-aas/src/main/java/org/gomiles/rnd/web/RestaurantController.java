package org.gomiles.rnd.web;

import java.util.List;

import org.gomiles.exception.GoMilesException;
import org.gomiles.rnd.mongodb.dto.QBox;
import org.gomiles.rnd.mongodb.dto.QCircle;
import org.gomiles.rnd.mongodb.dto.QPolygon;
import org.gomiles.rnd.mongodb.dto.QSphere;
import org.gomiles.rnd.mongodb.model.Restaurant;
import org.gomiles.rnd.mongodb.repository.GeoSpatialRepository;
import org.gomiles.rnd.mongodb.repository.RestaurantMongoDBRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/restaurant")
public class RestaurantController {

	private static final Logger LOG = LoggerFactory.getLogger(RestaurantController.class);

	@Autowired
	RestaurantMongoDBRepository restaurantRepository;
	@Autowired
	GeoSpatialRepository geoSpatialRepository;

	@PostMapping("/")
	@ResponseBody
	public Restaurant save(@RequestBody Restaurant restaurant) throws GoMilesException {
		restaurantRepository.save(restaurant);
		return restaurant;
	}

	@GetMapping("/{id}")
	@ResponseBody
	public Restaurant findById(@PathVariable("id") String id) throws GoMilesException {
		return restaurantRepository.findById(id).get();
	}

	@DeleteMapping("/{id}")
	@ResponseBody
	public Restaurant delete(@PathVariable("id") String id) throws GoMilesException {
		Restaurant restaurant = restaurantRepository.findById(id).get();
		restaurantRepository.save(restaurant);
		return restaurant;
	}

	@PostMapping("/within/polygon")
	@ResponseBody
	public List<Restaurant> withinPolygon(@RequestBody QPolygon qPolygon) throws GoMilesException {
		List<Restaurant> restaurants = restaurantRepository.findByPositionWithin(qPolygon.getPolygon());
		LOG.info("Restaurants#1: {}", restaurants.size());
		LOG.info("Restaurants#2: {}", geoSpatialRepository.within(qPolygon).size());
		return restaurants;
	}

	@PostMapping("/within/circle")
	@ResponseBody
	public List<Restaurant> withinPolygon(@RequestBody QCircle qCircle) throws GoMilesException {
		List<Restaurant> restaurants = restaurantRepository.findByPositionWithin(qCircle.getCircle());
		LOG.info("Restaurants#1: {}", restaurants.size());
		LOG.info("Restaurants#2: {}", geoSpatialRepository.within(qCircle).size());
		return restaurants;
	}

	@PostMapping("/within/box")
	@ResponseBody
	public List<Restaurant> withinPolygon(@RequestBody QBox qBox) throws GoMilesException {
		List<Restaurant> restaurants = restaurantRepository.findByPositionWithin(qBox.getBox());
		LOG.info("Restaurants#1: {}", restaurants.size());
		LOG.info("Restaurants#2: {}", geoSpatialRepository.within(qBox).size());
		return restaurants;
	}

	@PostMapping("/within/sphere")
	@ResponseBody
	public List<Restaurant> withinPolygon(@RequestBody QSphere qSphere) throws GoMilesException {
		List<Restaurant> restaurants = restaurantRepository.findByPositionWithin(qSphere.getSphere());
		LOG.info("Restaurants#1: {}", restaurants.size());
		LOG.info("Restaurants#2: {}", geoSpatialRepository.within(qSphere).size());
		return restaurants;
	}

	@PostMapping("/intersects/polygon")
	@ResponseBody
	public List<Restaurant> intersectsPolygon(@RequestBody QPolygon qPolygon) throws GoMilesException {
		List<Restaurant> restaurants = geoSpatialRepository.findIntersectingResources(qPolygon);
		LOG.info("Restaurants#1: {}", restaurants.size());
		return restaurants;
	}

}
