package org.gomiles.rnd.web;

import java.util.List;
import java.util.stream.Collectors;

import org.gomiles.rnd.cassandra.repository.RestaurantCassandraRepository;
import org.gomiles.rnd.dto.ContactDto;
import org.gomiles.rnd.dto.PositionDto;
import org.gomiles.rnd.dto.QRestaurantDto;
import org.gomiles.rnd.dto.RestaurantDto;
import org.gomiles.rnd.elasticsearch.repository.RestaurantEsRepository;
import org.gomiles.rnd.mongodb.repository.RestaurantMongoDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/kafka")
public class KafkaController {

	@Value("${gomiles.enrichment.topic-name}")
	String topic;

	@Autowired
	KafkaTemplate<String, RestaurantDto> kafkaTemplate;

	@Autowired
	RestaurantCassandraRepository restaurantCassandraRepository;
	@Autowired
	RestaurantMongoDBRepository restaurantMongoDBRepository;
	@Autowired
	RestaurantEsRepository restaurantEsRepository;

	@PostMapping("/publish")
	@ResponseBody
	public QRestaurantDto publish(@RequestBody QRestaurantDto qRestaurantDto) throws Exception {
		RestaurantDto dto = toDto(qRestaurantDto);
		kafkaTemplate.send(topic, dto);
		return qRestaurantDto;
	}

	private RestaurantDto toDto(QRestaurantDto q) {
		PositionDto pDto = PositionDto.newBuilder().setLat(q.getPosition().getLat()).setLon(q.getPosition().getLon())
				.build();
		List<ContactDto> cDtos = q.getContacts().stream()
				.map(c -> ContactDto.newBuilder().setContact(c.getContact()).setType(c.getType()).build())
				.collect(Collectors.toList());

		RestaurantDto dto = RestaurantDto.newBuilder().setName(q.getName()).setCity(q.getCity())
				.setCountry(q.getCountry()).setState(q.getState()).setPostalCode(q.getPostalCode()).setContacts(cDtos)
				.setPosition(pDto).build();
		return dto;
	}
}
