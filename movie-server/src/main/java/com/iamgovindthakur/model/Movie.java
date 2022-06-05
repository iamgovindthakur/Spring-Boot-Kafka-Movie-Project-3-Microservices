package com.iamgovindthakur.model;

import lombok.Data;

@Data
public class Movie {

	private int id;
	private String title;
	private int rating;
	private String genres;
	private boolean availability;

}
