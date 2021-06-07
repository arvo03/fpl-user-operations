package com.fpl.operations.microservice.exceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class FplUserException extends RuntimeException {

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
