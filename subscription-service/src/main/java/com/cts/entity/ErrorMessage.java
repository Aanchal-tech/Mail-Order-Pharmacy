package com.cts.entity;


import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessage 
{
	// http status
	private HttpStatus status;
	
	//timestamp
	private LocalDateTime timestamp;
	
	//message
	private String messge;


	
}