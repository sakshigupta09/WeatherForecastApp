package com.weather.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DailyForecast {
	
	private List<DailyForecastData> data;

	public DailyForecastData getData() {
		return data.get(0);
	}

	public void setData(List<DailyForecastData> data) {
		this.data = data;
	}
	
	
	
}
