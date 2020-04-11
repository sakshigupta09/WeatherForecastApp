package com.weather.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherForecastResponse {

	private int longitude;
	private int latitude;
	private CurrentForecast currently;
	private DailyForecast daily;

	public CurrentForecast getCurrently() {
		return currently;
	}
	public void setCurrently(CurrentForecast currently) {
		this.currently = currently;
	}
	public int getLongitude() {
		return longitude;
	}
	public void setLongitude(int longitude) {
		this.longitude = longitude;
	}
	public int getLatitude() {
		return latitude;
	}
	public void setLatitude(int latitude) {
		this.latitude = latitude;
	}
	public DailyForecast getDaily() {
		return daily;
	}
	public void setDaily(DailyForecast daily) {
		this.daily = daily;
	}
	
	
}
