package org.gomiles.rnd.cassandra.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.gomiles.rnd.cassandra.model.Restaurant;
import org.gomiles.rnd.cassandra.model.RestaurantByName;
import org.gomiles.rnd.cassandra.model.RestaurantByNameKey;
import org.springframework.data.cassandra.core.CassandraBatchOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.repository.query.CassandraEntityInformation;
import org.springframework.data.cassandra.repository.support.SimpleCassandraRepository;

public class RestaurantsRepositoryImpl extends SimpleCassandraRepository<Restaurant, UUID>
		implements RestaurantCassandraRepository {

	private final CassandraTemplate cassandraTemplate;
	private final RestaurantByNameRepository restaurantByNameRepository;

	public RestaurantsRepositoryImpl(CassandraEntityInformation<Restaurant, UUID> metadata,
			CassandraTemplate cassandraTemplate, RestaurantByNameRepository restaurantByNameRepository) {
		super(metadata, cassandraTemplate);
		this.cassandraTemplate = cassandraTemplate;
		this.restaurantByNameRepository = restaurantByNameRepository;
	}

	@Override
	public <S extends Restaurant> S insert(S restaurant) {
		restaurant.setUuid(UUID.randomUUID());
		CassandraBatchOperations batchOps = cassandraTemplate.batchOps();
		insertByName(restaurant, batchOps);
		batchOps.insert(restaurant);
		batchOps.execute();
		return restaurant;
	}

	private <S extends Restaurant> void insertByName(S restaurant, CassandraBatchOperations batchOps) {
		RestaurantByNameKey key = new RestaurantByNameKey();
		key.setName(restaurant.getName());
		key.setUuid(restaurant.getUuid());
		RestaurantByName restaurantByName = new RestaurantByName();
		restaurantByName.setRestaurantByNameKey(key);
		restaurantByName.setPosition(restaurant.getPosition());
		batchOps.insert(restaurantByName);
	}

	@Override
	public void delete(Restaurant restaurant) {
		CassandraBatchOperations batchOps = cassandraTemplate.batchOps();
		deleteByName(restaurant, batchOps);
		batchOps.execute();
	}

	private void deleteByName(Restaurant restaurant, CassandraBatchOperations batchOps) {
		batchOps.delete(restaurantByNameRepository.findByRestaurantByNameKey(
				new RestaurantByNameKey().setName(restaurant.getName()).setUuid(restaurant.getUuid())));
	}

	@Override
	public void deleteById(UUID id) {
		findById(id).ifPresent(this::delete);
	}

	@Override
	public void deleteAll() {
		deleteAll(findAll());
	}

	@Override
	public void deleteAll(Iterable<? extends Restaurant> restaurants) {
		restaurants.forEach(this::delete);
	}

	@Override
	public <S extends Restaurant> List<S> insert(Iterable<S> restaurants) {
		final List<S> result = new ArrayList<>();
		for (final S restaurant : restaurants) {
			result.add(insert(restaurant));
		}
		return result;
	}

	@Override
	public <S extends Restaurant> S save(final S restaurant) {
		return insert(restaurant);
	}

	@Override
	public <S extends Restaurant> List<S> saveAll(final Iterable<S> restaurants) {
		return insert(restaurants);
	}
}
