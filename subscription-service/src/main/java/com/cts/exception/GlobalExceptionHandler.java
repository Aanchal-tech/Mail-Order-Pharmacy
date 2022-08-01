package com.cts.exception;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cts.entity.ErrorMessage;



@RestControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	

	@ExceptionHandler(InvalidTokenException.class)
	public ResponseEntity<ErrorMessage> invalidTokenException(InvalidTokenException invalidTokenException) {
		return new ResponseEntity<>(
				new ErrorMessage(HttpStatus.UNAUTHORIZED, LocalDateTime.now(), invalidTokenException.getMessage()),
				HttpStatus.UNAUTHORIZED);
	}


	@ExceptionHandler(SubscriptionListEmptyException.class)
	public  ResponseEntity<ErrorMessage> subscriptionListEmptyException(SubscriptionListEmptyException subscriptionListEmptyException) {
		return new ResponseEntity<>(
				new ErrorMessage(HttpStatus.NOT_FOUND, LocalDateTime.now(), subscriptionListEmptyException.getMessage()),
				HttpStatus.NOT_FOUND);
	}


	@ExceptionHandler(feign.RetryableException.class)
	@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
	public ErrorMessage serviceUnavailableException() {
		return new ErrorMessage(HttpStatus.SERVICE_UNAVAILABLE, LocalDateTime.now(), "Temporarily service unavailable");
	}

}