package com.sirius.trip.tripapi.payload;

import java.util.Set;

import lombok.Data;

@Data
public class SignupRequest {
	 private String username;
	 
	    private String email;
	    
	    private Set<String> roles;
	    
	    private String password;
}
