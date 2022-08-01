package com.cts.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TokenValid {
	
	// User Id
	private String uid;
	
	// User Name 
	private String name;
	
	// Validity check for the token 
	private boolean isValid;

	
	
}
