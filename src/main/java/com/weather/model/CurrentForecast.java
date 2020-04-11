package com.weather.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.weather.util.LocalDateTimeDeserializer;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrentForecast {

public CurrentForecast() {
}
	
private double temperature;
@JsonDeserialize(using = LocalDateTimeDeserializer.class)
private LocalDateTime time;


public LocalDateTime getTime() {
	return time;
}

public void setTime(LocalDateTime time) {
	this.time = time;
}
	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	
}
