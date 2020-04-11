package com.weather.service;

import com.weather.model.WeatherForecastResponse;

public interface ForecastService {

	public WeatherForecastResponse getForcastFor(String longitude, String latitude);

}
