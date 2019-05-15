package org.gomiles.rnd.redis.repository;

import java.util.List;

import org.gomiles.rnd.redis.model.Restaurant;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RestaurantRsRepository extends PagingAndSortingRepository<Restaurant, Integer> {

	List<Restaurant> findByName(String name);

	List<Restaurant> findByCity(String name);

	List<Restaurant> findByCountry(String name);
}
