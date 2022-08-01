package com.cts.exception;


@SuppressWarnings("serial")
public class DrugNotFoundException extends RuntimeException {
    public DrugNotFoundException(final String message) {
        super(message);
    }
}
