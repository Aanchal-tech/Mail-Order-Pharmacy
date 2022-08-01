package com.cts.exception;

@SuppressWarnings("serial")
public class StockNotFoundException extends Exception{
    public StockNotFoundException(final String message) {
        super(message);
    }
}
