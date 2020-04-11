package com.weather.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.weather.util.LocalDateTimeDeserializer;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DailyForecastData{

	private double temperatureMin;
	private double temperatureMax;

	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime temperatureMinTime;
	
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime temperatureMaxTime;
	
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime sunriseTime;
	
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime sunsetTime;

	public double getTemperatureMin() {
		return temperatureMin;
	}

	public void setTemperatureMin(double temperatureMin) {
		this.temperatureMin = temperatureMin;
	}

	public LocalDateTime getTemperatureMinTime() {
		return temperatureMinTime;
	}

	public void setTemperatureMinTime(LocalDateTime temperatureMinTime) {
		this.temperatureMinTime = temperatureMinTime;
	}

	public double getTemperatureMax() {
		return temperatureMax;
	}

	public void setTemperatureMax(double temperatureMax) {
		this.temperatureMax = temperatureMax;
	}

	public LocalDateTime getTemperatureMaxTime() {
		return temperatureMaxTime;
	}

	public void setTemperatureMaxTime(LocalDateTime temperatureMaxTime) {
		this.temperatureMaxTime = temperatureMaxTime;
	}

	public LocalDateTime getSunriseTime() {
		return sunriseTime;
	}

	public void setSunriseTime(LocalDateTime sunriseTime) {
		this.sunriseTime = sunriseTime;
	}

	public LocalDateTime getSunsetTime() {
		return sunsetTime;
	}

	public void setSunsetTime(LocalDateTime sunsetTime) {
		this.sunsetTime = sunsetTime;
	}

	
	
}
