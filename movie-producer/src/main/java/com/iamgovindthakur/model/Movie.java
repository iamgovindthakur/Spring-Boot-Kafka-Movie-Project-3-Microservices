package com.iamgovindthakur.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

	
	private int id;
	private String title;
	private int rating;
	private String genres;
	private boolean availability;

}
