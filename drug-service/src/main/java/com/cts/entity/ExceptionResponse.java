package com.cts.entity;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExceptionResponse {
	  String messge;
	    LocalDateTime timestamp;
	    HttpStatus status;
}
