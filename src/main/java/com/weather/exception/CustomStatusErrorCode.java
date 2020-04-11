package com.weather.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Latitude or longtitude is null. Please correct the input") 
public class CustomStatusErrorCode extends RuntimeException{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
}
