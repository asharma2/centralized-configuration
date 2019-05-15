package org.gomiles.rnd.cassandra.config;

import java.util.UUID;

import org.gomiles.rnd.cassandra.model.Restaurant;
import org.gomiles.rnd.cassandra.repository.RestaurantByNameRepository;
import org.gomiles.rnd.cassandra.repository.RestaurantCassandraRepository;
import org.gomiles.rnd.cassandra.repository.RestaurantsRepositoryImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.core.mapping.CassandraPersistentEntity;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import org.springframework.data.cassandra.repository.query.CassandraEntityInformation;
import org.springframework.data.cassandra.repository.support.MappingCassandraEntityInformation;

@Configuration
@EnableCassandraRepositories(basePackages = "org.gomiles.rnd.cassandra.repository")
public class CassandraConfig extends AbstractCassandraConfiguration {

	@Value("${spring.data.cassandra.keyspace-name}")
	String keyspace;

	@Override
	public SchemaAction getSchemaAction() {
		return SchemaAction.CREATE_IF_NOT_EXISTS;
	}

	@Override
	public String[] getEntityBasePackages() {
		return new String[] { "org.gomiles.rnd.cassandra.model" };
	}

	@Override
	protected String getKeyspaceName() {
		return keyspace;
	}

	@Override
	protected boolean getMetricsEnabled() {
		return false;
	}

	// @Override
	// public CassandraMappingContext cassandraMapping() throws
	// ClassNotFoundException {
	// CassandraMappingContext mappingContext = new CassandraMappingContext();
	// mappingContext.setUserTypeResolver(new
	// SimpleUserTypeResolver(cluster().getObject(), keyspace));
	// mappingContext.setInitialEntitySet(getInitialEntitySet());
	// return mappingContext;
	// }

	@SuppressWarnings("unchecked")
	@Bean
	public RestaurantCassandraRepository restaurantsRepository(final CassandraTemplate cassandraTemplate,
			final RestaurantByNameRepository restaurantByNameRepository) {

		final CassandraPersistentEntity<?> entity = cassandraTemplate.getConverter().getMappingContext()
				.getRequiredPersistentEntity(Restaurant.class);

		final CassandraEntityInformation<Restaurant, UUID> metadata = new MappingCassandraEntityInformation<>(
				(CassandraPersistentEntity<Restaurant>) entity, cassandraTemplate.getConverter());

		return new RestaurantsRepositoryImpl(metadata, cassandraTemplate, restaurantByNameRepository);
	}

}
