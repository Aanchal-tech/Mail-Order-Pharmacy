package com.cts.exception;

/**Custom exception class*/

@SuppressWarnings("serial")
public class InvalidTokenException extends RuntimeException {


	public InvalidTokenException(String message){
		super(message);
	}
}
