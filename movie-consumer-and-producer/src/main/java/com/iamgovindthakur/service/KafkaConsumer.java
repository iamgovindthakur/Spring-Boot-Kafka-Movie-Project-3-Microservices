package com.iamgovindthakur.service;

import java.io.IOException;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iamgovindthakur.model.Movie;

@Service
public class KafkaConsumer {

	@Autowired
	private KafkaTemplate<String, Movie> kafkaTemplate;

	private static final String TOPIC = "Kafka_Example";
	private static final String TOPIC_ACTIVE = "active_movie";
	private static final String TOPIC_NOT_ACTIVE = "not_active_movie";
	private static final String GROUP_ID = "group_id";

	protected String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}

	protected <T> T mapFromJson(String json, Class<T> clazz)
			throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, clazz);
	}

	@KafkaListener(topics = TOPIC, groupId = GROUP_ID)
	public void consume(ConsumerRecord<String, String> payload)
			throws JsonParseException, JsonMappingException, IOException {
		Movie movie = mapFromJson(payload.value(), Movie.class);

		if (movie.isAvailability()) {
			kafkaTemplate.send(TOPIC_ACTIVE, movie);
		}

		else {
			kafkaTemplate.send(TOPIC_NOT_ACTIVE, movie);
		}
	}
}
