package org.gomiles.rnd.kafka.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.gomiles.avro.deserializer.AvroDeserializer;
import org.gomiles.rnd.dto.RestaurantDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

@Configuration
@EnableKafka
public class RestaurantConsumerConfig {

	@Value("${spring.kafka.bootstrap-servers}")
	String bootstrapServer;
	@Value("${spring.kafka.consumer.group-id}")
	String groupId;

	@Bean
	Map<String, Object> consumerConfigs() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, AvroDeserializer.class);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
		return props;
	}

	@Bean
	ConsumerFactory<String, RestaurantDto> consumerFactory() {
		return new DefaultKafkaConsumerFactory<>(consumerConfigs(), new StringDeserializer(),
				new AvroDeserializer<>(RestaurantDto.class));
	}

	@Bean
	ConcurrentKafkaListenerContainerFactory<String, RestaurantDto> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, RestaurantDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}
}
