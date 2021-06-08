package com.fpl.operations.microservice.exceptionHandling;

import java.util.Date;

public class FplException {
	Date timestamp;
	String message;
	String details;
	public FplException(Date timestamp, String message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public String getMessage() {
		return message;
	}
	public String getDetails() {
		return details;
	}
	
	
}
