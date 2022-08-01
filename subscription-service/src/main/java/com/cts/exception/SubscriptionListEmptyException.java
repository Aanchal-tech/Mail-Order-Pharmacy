package com.cts.exception;

/**Custom exception class*/

@SuppressWarnings("serial")
public class SubscriptionListEmptyException extends RuntimeException {


	public SubscriptionListEmptyException(String message){
		super(message);
	}
}
