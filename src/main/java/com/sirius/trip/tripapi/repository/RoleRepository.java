package com.sirius.trip.tripapi.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sirius.trip.tripapi.model.ERole;
import com.sirius.trip.tripapi.model.Role;

public interface RoleRepository extends MongoRepository<Role, String> {
	Optional<Role> findByName(ERole name);
}
