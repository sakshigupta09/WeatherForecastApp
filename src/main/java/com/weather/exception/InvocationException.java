package com.weather.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_GATEWAY, reason="Service invocation failed") 
public class InvocationException extends RuntimeException {

	private String serviceName;
	private int httpStatusCode;
	private String externalResultStatusCode;
	
	private static final long serialVersionUID = 2675490592867878989L;

	public InvocationException(String serviceName, String resultStatusCode) {
		this.serviceName = serviceName;
		this.externalResultStatusCode =resultStatusCode;
	}

	public InvocationException(String serviceName, int httpStatusCode) {
		this.serviceName = serviceName;
		this.httpStatusCode = httpStatusCode;
	}

	public InvocationException(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public int getExternalServiceHTTPStatusCode() {
		return httpStatusCode;
	}

	public void setExternalServiceHTTPStatusCode(int httpStatusCode) {
		this.httpStatusCode = httpStatusCode;
	}

	public String getExternalResultStatusCode() {
		return externalResultStatusCode;
	}

	public void setExternalResultStatusCode(String externalResultStatusCode) {
		this.externalResultStatusCode = externalResultStatusCode;
	}
	
	
}
