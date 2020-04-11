package com.weather.service;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.weather.exception.GatewayException;
import com.weather.exception.InvocationException;
import com.weather.model.WeatherForecastResponse;

@Service
public class ForecastServiceImpl implements ForecastService {

	public static final String FORECAST_IO_SERVICE_NAME = "ForecastIOException";
	protected static final String ARG_LATITUDE = "latitude";
	protected static final String ARG_LONGITUDE = "longitude";
	protected static final String ARG_API_KEY = "apiKey";

	@Autowired
	private RestTemplate restTemplate;

	@Value("${darksky.url}")
	public String darkskyUrl;
	
	@Value("${darksky.apiKey}") 
	public String darkskyApiKey;
	
	public WeatherForecastResponse getForcastFor(String longitude, String latitude) {
		try {
			WeatherForecastResponse forecast = restTemplate.getForObject(darkskyUrl, 
					WeatherForecastResponse.class,buildURLMap(longitude,latitude));
		return forecast;
		} catch (HttpStatusCodeException httpStatusEx) {
			throw new InvocationException(FORECAST_IO_SERVICE_NAME, httpStatusEx.getRawStatusCode());
		} catch (Exception ex) {
			// This is thrown when can't even get to API (e.g. network error)!
			throw new GatewayException(FORECAST_IO_SERVICE_NAME, ex);
		}
		
	}
	
	Map<String,String> buildURLMap(String longitude, String latitude) {
		Map<String,String> arguments = new HashMap<String,String>();
		arguments.put(ARG_API_KEY, darkskyApiKey);
		arguments.put(ARG_LONGITUDE, longitude);
		arguments.put(ARG_LATITUDE, latitude);
		return arguments;
	}
	
}
