package com.cts.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
	
	// User Id 
	private String uid;
	
	// User Name 
	private String name;
	
	// Validity check 
	private boolean isValid;

	
}
