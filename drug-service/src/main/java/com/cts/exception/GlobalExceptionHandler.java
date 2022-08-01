package com.cts.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cts.entity.ExceptionResponse;

import feign.RetryableException;

@RestControllerAdvice 
public class GlobalExceptionHandler {
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@ExceptionHandler({ InvalidTokenException.class })
    public ResponseEntity<ExceptionResponse> invalidTokenException(final InvalidTokenException invalidTokenException) {
        return (ResponseEntity<ExceptionResponse>)new ResponseEntity((Object)new ExceptionResponse(invalidTokenException.getMessage(), LocalDateTime.now(), HttpStatus.UNAUTHORIZED), HttpStatus.UNAUTHORIZED);
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@ExceptionHandler({ DrugNotFoundException.class })
    public ResponseEntity<ExceptionResponse> drugNotFoundException(final DrugNotFoundException drugNotFoundException) {
        return (ResponseEntity<ExceptionResponse>)new ResponseEntity((Object)new ExceptionResponse(drugNotFoundException.getMessage(), LocalDateTime.now(), HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })    
    @ExceptionHandler({ StockNotFoundException.class })
    public ResponseEntity<ExceptionResponse> stockNotFoundException(final StockNotFoundException stockNotFoundException) {
        return (ResponseEntity<ExceptionResponse>)new ResponseEntity((Object)new ExceptionResponse(stockNotFoundException.getMessage(), LocalDateTime.now(), HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })    
    @ExceptionHandler({ RetryableException.class })
    public ResponseEntity<ExceptionResponse> microServiceUnavailableException() {
        return (ResponseEntity<ExceptionResponse>)new ResponseEntity((Object)new ExceptionResponse("MicroServiceUnavailable", LocalDateTime.now(), HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
    }   
}
