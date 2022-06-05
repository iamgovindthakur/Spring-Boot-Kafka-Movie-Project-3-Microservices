//package com.iamgovindthakur.controller;
//
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.List;
//import java.util.concurrent.atomic.AtomicInteger;
//import java.util.stream.Collectors;
//
//import com.fasterxml.jackson.core.exc.StreamReadException;
//import com.fasterxml.jackson.databind.DatabindException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.type.CollectionType;
//import com.google.gson.Gson;
//import com.iamgovindthakur.model.Movie;
//
//public class Demo {
//
//	public static void main(String[] args) throws StreamReadException, DatabindException, IOException {
//		// TODO Auto-generated method stub
//
//		ObjectMapper mapper = new ObjectMapper();
//		InputStream inputStream = MovieController.class.getClassLoader().getResourceAsStream("MOCK_DATA.json");
//		InputStream inputStream1 = MovieController.class.getClassLoader().getResourceAsStream("Movie Data1.json");
//		InputStream inputStream2 = MovieController.class.getClassLoader().getResourceAsStream("Movie Data1.json");
//		CollectionType collectionType = mapper.getTypeFactory().constructCollectionType(List.class, Movie.class);
//		List<Movie> lstmovies = mapper.readValue(inputStream, collectionType);
//		lstmovies.addAll(mapper.readValue(inputStream1, collectionType));
//		lstmovies.addAll(mapper.readValue(inputStream2, collectionType));
//		AtomicInteger count = new AtomicInteger(1);
//		System.out.println(lstmovies.size());
//		lstmovies = lstmovies.stream().map(movie -> {
//			movie.setId(count.getAndIncrement());
//			return movie;
//		}).collect(Collectors.toList());
//		// System.out.println(lstmovies.size());
//
//		File file = new File("JsonFile.json");
//		file.createNewFile();
//		FileWriter fileWriter = new FileWriter(file);
//		System.out.println("Writing JSON object to file");
//		System.out.println("-----------------------");
//		// System.out.print(lstmovies);
//
//		Gson gson = new Gson();
//		String jsonCartList = gson.toJson(lstmovies);
//		fileWriter.write(jsonCartList);
//		fileWriter.flush();
//		fileWriter.close();
//
//	}
//
//}
