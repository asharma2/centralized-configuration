package org.gomiles.rnd.cassandra.model;

import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import com.datastax.driver.core.DataType;

@Table("restaurants_by_name")
public class RestaurantByName {

	@PrimaryKey
	RestaurantByNameKey restaurantByNameKey;

	@CassandraType(type = DataType.Name.UDT, userTypeName = "position")
	Position position;

	public RestaurantByNameKey getRestaurantByNameKey() {
		return restaurantByNameKey;
	}

	public void setRestaurantByNameKey(RestaurantByNameKey restaurantByNameKey) {
		this.restaurantByNameKey = restaurantByNameKey;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

}
