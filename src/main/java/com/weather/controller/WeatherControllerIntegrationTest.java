package com.weather.controller;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.weather.model.WeatherForecastResponse;
import com.weather.service.ForecastService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class WeatherControllerIntegrationTest extends AbstractJUnit4SpringContextTests{
	
	@Mock
	ForecastService forecastService;
	
	@InjectMocks
	WeatherController weatherController;
	
    private MockMvc mockMvc;
    
    @Before
    public void setUp() {
    	weatherController=new WeatherController();
		MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(weatherController).build();

    }
    
    @Test
	public void testWeatherController() throws Exception { 
    	when(forecastService.getForcastFor("42.335190","-83.049190")).thenReturn(buildStubForecastResponse());
		 mockMvc.perform(
	                post("/weather/forecast/42.335190,-83.049190").accept(MediaType.APPLICATION_JSON)
	                        .contentType(MediaType.APPLICATION_JSON)).
		 andExpect(MockMvcResultMatchers.jsonPath("$.longitude", Matchers.is(100)));
  }
	
	private WeatherForecastResponse buildStubForecastResponse() {
		WeatherForecastResponse stubForecastResponse = new WeatherForecastResponse();
		stubForecastResponse.setLatitude(100);
		stubForecastResponse.setLongitude(100);
		return stubForecastResponse;
	}
	
}
