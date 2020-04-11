package com.weather.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.GATEWAY_TIMEOUT, reason="Service invocation failed") 
public class GatewayException extends RuntimeException {

	private static final long serialVersionUID = 1728342245949699094L;

	private String serviceName;
	private Exception innerException;
	
	public GatewayException(String serviceName, Exception innerException) {
		this.serviceName = serviceName;
		this.innerException = innerException;
	}
	
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public Exception getInnerException() {
		return innerException;
	}
	public void setInnerException(Exception innerException) {
		this.innerException = innerException;
	}
	
}
