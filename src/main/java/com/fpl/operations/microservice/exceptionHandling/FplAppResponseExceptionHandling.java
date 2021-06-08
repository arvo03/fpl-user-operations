package com.fpl.operations.microservice.exceptionHandling;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class FplAppResponseExceptionHandling extends ResponseEntityExceptionHandler{

	@ExceptionHandler(FplUserException.class)
	public final ResponseEntity<Object> handleUserNotFounddException(FplUserException ex, WebRequest request){
		FplException fplException = new FplException(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(fplException, ex.getCode());
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		FplException fplException = new FplException(new Date(), "Validation Failed", ex.getBindingResult().toString());
		return new ResponseEntity<>(fplException, HttpStatus.BAD_REQUEST);
	}
}
