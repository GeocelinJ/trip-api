package com.sirius.trip.tripapi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.sirius.trip.tripapi.model.TouristLocations;

@Repository
public interface TripApiRepository extends MongoRepository<TouristLocations, String>{

	
}
