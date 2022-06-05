package com.iamgovindthakur.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.iamgovindthakur.model.Movie;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("kafka")
@Log4j2
public class ProductController {

	@Autowired
	private KafkaTemplate<String, Movie> kafkaTemplate;

	private static final String TOPIC = "Kafka_Example";

	@GetMapping("/publish")
	public String post() {
		ResponseEntity<Movie[]> forEntity = new RestTemplate().getForEntity("http://localhost:8090/", Movie[].class);
		Movie[] body = forEntity.getBody();
		Arrays.asList(body).stream().forEach(movie -> {
			kafkaTemplate.send(TOPIC, movie);
		});

		return "Published successfully";
	}

	@Scheduled(cron = "0/5 * * ? * *")
	public void cronPost() {
		ResponseEntity<Movie[]> forEntity = new RestTemplate().getForEntity("http://localhost:8090/", Movie[].class);
		Movie[] body = forEntity.getBody();
		Arrays.asList(body).stream().forEach(movie -> {
			kafkaTemplate.send(TOPIC, movie);
		});

		log.info("Cron job run successfully by thread -> " + Thread.currentThread().getName());

	}
}
