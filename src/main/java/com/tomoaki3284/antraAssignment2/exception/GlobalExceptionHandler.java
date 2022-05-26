package com.tomoaki3284.antraAssignment2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(HttpClientErrorException.class)
	public ResponseEntity handleRuntimeException() {
		return new ResponseEntity("external api busy", HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
