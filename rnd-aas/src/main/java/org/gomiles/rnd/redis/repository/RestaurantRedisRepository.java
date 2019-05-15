package org.gomiles.rnd.redis.repository;

import java.util.List;

import javax.annotation.Resource;

import org.gomiles.rnd.redis.model.Restaurant;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.ValueOperations;

public class RestaurantRedisRepository {

	String CACHE_BY_NAME = "cache_by_name";
	String CACHE_BY_CITY = "cache_by_city";
	String CACHE_BY_COUNTRY = "cache_by_country";

	@Resource(name = "redisTemplate")
	HashOperations<String, String, Restaurant> opsForHash;
	ValueOperations<String, Restaurant> opsForValue;
	ListOperations<String, Restaurant> opsForList;

	public Restaurant save(Restaurant restaurant) {
		opsForHash.put(CACHE_BY_NAME, restaurant.getName(), restaurant);
		opsForHash.put(CACHE_BY_CITY, restaurant.getCity(), restaurant);
		opsForHash.put(CACHE_BY_COUNTRY, restaurant.getCountry(), restaurant);
		return restaurant;
	}

	public Restaurant findByName(String name) {
		return opsForHash.get(CACHE_BY_NAME, name);
	}

	public List<Restaurant> findByCity(String city) {
		return opsForHash.values(city);
	}

	public List<Restaurant> findByCountry(String country) {
		return opsForHash.values(country);
	}

	public void delete(Restaurant restaurant) {
		opsForHash.delete(CACHE_BY_NAME, restaurant);
		opsForHash.delete(CACHE_BY_CITY, restaurant);
		opsForHash.delete(CACHE_BY_COUNTRY, restaurant);
	}

}
