package org.gomiles.rnd.kafka.consumer;

import org.gomiles.rnd.dto.RestaurantDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class EnrichmentConsumer {

	private static final Logger LOG = LoggerFactory.getLogger(EnrichmentConsumer.class);

	@KafkaListener(topics = "${gomiles.enrichment.topic-name}")
	public void receive(RestaurantDto restaurantDto) {
		LOG.info("RestaurantDto: {}", restaurantDto);
	}
}
