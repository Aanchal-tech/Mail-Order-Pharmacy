package com.cts.exception;

@SuppressWarnings("serial")
public class InvalidTokenException extends Exception {
    public InvalidTokenException(final String message) {
        super(message);
    }
}
