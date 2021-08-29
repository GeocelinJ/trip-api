package com.sirius.trip.tripapi.model;


import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "user")
@Data
public class User {
	
	public User(String username, String password) {
	    this.username = username;
	    this.password = password;
	  }
	
	  @Id
	  private String id;

	  private String username;
	  
	  private String password;
	  
	  @DBRef
	  private Set<Role> roles = new HashSet<>();


}
