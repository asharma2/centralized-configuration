package org.gomiles.rnd.cassandra.repository;

import java.util.UUID;

import org.gomiles.rnd.cassandra.model.Restaurant;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface RestaurantCassandraRepository extends CassandraRepository<Restaurant, UUID> {

}
