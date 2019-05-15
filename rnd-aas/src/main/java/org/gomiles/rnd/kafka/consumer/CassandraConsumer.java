package org.gomiles.rnd.kafka.consumer;

import org.gomiles.rnd.dto.RestaurantDto;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class CassandraConsumer {

	@KafkaListener(topics = "${gomiles.enrichment.topic-name}")
	public void receive(RestaurantDto dto) {

	}
}
