package com.fpl.operations.microservice.exceptionHandling;

import org.springframework.http.HttpStatus;

public class FplUserException extends RuntimeException {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8263350881908382730L;
	private HttpStatus code;
	
	public FplUserException(String message) {
		super(message);
	}
	
	public FplUserException(String message, HttpStatus code) {
		super(message);
		this.code = code;
	}

	public HttpStatus getCode() {
		return code;
	}
	
}
