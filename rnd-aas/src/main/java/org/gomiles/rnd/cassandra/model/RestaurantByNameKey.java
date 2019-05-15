package org.gomiles.rnd.cassandra.model;

import java.util.UUID;

import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

@PrimaryKeyClass
public class RestaurantByNameKey {

	@PrimaryKeyColumn(name = "name", type = PrimaryKeyType.PARTITIONED)
	String name;
	@PrimaryKeyColumn(name = "id", ordinal = 0, ordering = Ordering.DESCENDING)
	UUID uuid;

	public String getName() {
		return name;
	}

	public RestaurantByNameKey setName(String name) {
		this.name = name;
		return this;
	}

	public UUID getUuid() {
		return uuid;
	}

	public RestaurantByNameKey setUuid(UUID uuid) {
		this.uuid = uuid;
		return this;
	}

}
