package org.gomiles.rnd.cassandra.repository;

import java.util.List;

import org.gomiles.rnd.cassandra.model.RestaurantByName;
import org.gomiles.rnd.cassandra.model.RestaurantByNameKey;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantByNameRepository extends CassandraRepository<RestaurantByName, RestaurantByNameKey> {

	@Query(allowFiltering = true)
	List<RestaurantByName> findByRestaurantByNameKey(RestaurantByNameKey key);
}
