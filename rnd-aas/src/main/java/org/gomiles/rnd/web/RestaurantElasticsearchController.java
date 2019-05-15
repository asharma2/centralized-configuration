package org.gomiles.rnd.web;

import org.gomiles.exception.GoMilesException;
import org.gomiles.rnd.elasticsearch.model.Restaurant;
import org.gomiles.rnd.elasticsearch.repository.RestaurantEsRepository;
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
@RequestMapping("/api/es/restaurant")
public class RestaurantElasticsearchController {

	private static final Logger LOG = LoggerFactory.getLogger(RestaurantElasticsearchController.class);

	@Autowired
	RestaurantEsRepository restaurantEsRepository;

	@PostMapping("/")
	@ResponseBody
	public Restaurant save(@RequestBody Restaurant restaurant) throws GoMilesException {
		LOG.info("Going to persist the restaurant");
		restaurantEsRepository.save(restaurant);
		return restaurant;
	}

	@GetMapping("/{id}")
	@ResponseBody
	public Restaurant findById(@PathVariable("id") String id) throws GoMilesException {
		LOG.info("Going to get the restaurant");
		return restaurantEsRepository.findById(id).get();
	}

	@DeleteMapping("/{id}")
	@ResponseBody
	public Restaurant delete(@PathVariable("id") String id) throws GoMilesException {
		LOG.info("Going to delete the restaurant");
		Restaurant restaurant = restaurantEsRepository.findById(id).get();
		restaurantEsRepository.delete(restaurant);
		return restaurant;
	}

}
