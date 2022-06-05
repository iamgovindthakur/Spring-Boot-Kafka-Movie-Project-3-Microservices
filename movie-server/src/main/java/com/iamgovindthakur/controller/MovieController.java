package com.iamgovindthakur.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.iamgovindthakur.model.Movie;

@RestController
public class MovieController {

	@GetMapping
	public List<Movie> getMovie() throws StreamReadException, DatabindException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		InputStream inputStream = MovieController.class.getClassLoader().getResourceAsStream("3000MoviesData.json");
		CollectionType collectionType = mapper.getTypeFactory().constructCollectionType(List.class, Movie.class);
		List<Movie> lstmovies = mapper.readValue(inputStream, collectionType);

		return lstmovies;

	}

}
