package org.gomiles.rnd.kafka.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.gomiles.avro.serializer.AvroSerializer;
import org.gomiles.rnd.dto.RestaurantDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class RestaurantProducerConfig {

	@Value("${spring.kafka.bootstrap-servers}")
	String bootstrapServer;
	@Value("${spring.kafka.consumer.group-id}")
	String groupId;
	@Value("${spring.kafka.consumer.auto-offset-reset}")
	String autoOffsetReset;

	@Value("${gomiles.enrichment.topic-name}")
	String topic;

	@Bean
	public Map<String, Object> producerConfigs() {
		Map<String, Object> props = new HashMap<>();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, AvroSerializer.class);
		return props;
	}

	@Bean
	public ProducerFactory<String, RestaurantDto> producerFactory() {
		return new DefaultKafkaProducerFactory<>(producerConfigs());
	}

	@Bean
	public KafkaTemplate<String, RestaurantDto> kafkaTemplate() {
		return new KafkaTemplate<>(producerFactory());
	}

//	@Bean
//	public NewTopic adviceTopic() {
//		return new NewTopic(topic, 3, (short) 1);
//	}

}
