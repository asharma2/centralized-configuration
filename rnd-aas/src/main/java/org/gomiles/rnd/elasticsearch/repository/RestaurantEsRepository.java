package org.gomiles.rnd.elasticsearch.repository;

import org.gomiles.rnd.elasticsearch.model.Restaurant;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;

public interface RestaurantEsRepository extends ElasticsearchCrudRepository<Restaurant, String> {

}
