package org.gomiles.rnd.web;

import java.util.List;

import org.gomiles.exception.GoMilesException;
import org.gomiles.rnd.redis.model.Restaurant;
import org.gomiles.rnd.redis.repository.RestaurantRsRepository;
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
@RequestMapping("/api/redis/restaurant")
public class RestaurantRedisController {

	private static final Logger LOG = LoggerFactory.getLogger(RestaurantRedisController.class);

	@Autowired
	RestaurantRsRepository restaurantRsRepository;

	@PostMapping("/")
	@ResponseBody
	public Restaurant save(@RequestBody Restaurant restaurant) throws GoMilesException {
		LOG.info("Going to persist the restaurant");
		restaurantRsRepository.save(restaurant);
		return restaurant;
	}

	@GetMapping("/name/{name}")
	@ResponseBody
	public List<Restaurant> findByName(@PathVariable("name") String name) throws GoMilesException {
		LOG.info("Going to get the restaurant");
		return restaurantRsRepository.findByName(name);
	}

	@GetMapping("/city/{city}")
	@ResponseBody
	public List<Restaurant> findByCity(@PathVariable("city") String city) throws GoMilesException {
		LOG.info("Going to get the restaurant");
		return restaurantRsRepository.findByCity(city);
	}

	@GetMapping("/country/{country}")
	@ResponseBody
	public List<Restaurant> findByState(@PathVariable("country") String country) throws GoMilesException {
		LOG.info("Going to get the restaurant");
		return restaurantRsRepository.findByCountry(country);
	}

	@DeleteMapping("/{name}")
	@ResponseBody
	public List<Restaurant> delete(@PathVariable("name") String name) throws GoMilesException {
		LOG.info("Going to delete the restaurant");
		List<Restaurant> restaurants = restaurantRsRepository.findByName(name);
		restaurants.forEach(restaurantRsRepository::delete);
		return restaurants;
	}

}
