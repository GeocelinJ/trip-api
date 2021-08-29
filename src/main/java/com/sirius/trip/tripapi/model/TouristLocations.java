package com.sirius.trip.tripapi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "tourist_locations")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TouristLocations {
	
	@Id
	private String location;
	
	private String state;
	
	private Integer popularity;
}
