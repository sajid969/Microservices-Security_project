package com.tcs.exceptionalhandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice

public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFound.class)
	public ResponseEntity<String> resourceNotFoundException(ResourceNotFound message){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message.getMessage());
		
	}

}
