package com.weather.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.weather.exception.CustomStatusErrorCode;
import com.weather.model.WeatherForecastResponse;
import com.weather.service.ForecastService;

@RestController
@RequestMapping(value = "/weather")
public class WeatherController {

	@Autowired
	ForecastService forecastService;
	
	@RequestMapping(value = "/forecast/{lat},{long}", method=RequestMethod.GET, produces="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public WeatherForecastResponse getForecast(@PathVariable(value = "lat") String latitude,
			@PathVariable(value = "long") String longitude) {
		
		if(StringUtils.isEmpty(latitude) || StringUtils.isEmpty(longitude)) {
			throw new CustomStatusErrorCode();
		}
		WeatherForecastResponse forecastResponse = forecastService.getForcastFor(latitude,longitude);
		
		return forecastResponse;
	}
	
	
}
