package com.weather.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.hamcrest.core.StringStartsWith;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.client.response.MockRestResponseCreators;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weather.exception.GatewayException;
import com.weather.exception.InvocationException;
import com.weather.model.WeatherForecastResponse;

@SpringBootTest
@PropertySource("classpath:/app-test.properties")
public class ForecastRetrieverImplIntegrationTest extends AbstractJUnit4SpringContextTests {

	@Autowired
	private ForecastServiceImpl service;
	
	@Autowired
	private RestTemplate restTemplate;
	
	private MockRestServiceServer mockServer;
	
	@Before
	public void setUp() throws Exception {
		mockServer = MockRestServiceServer.createServer(restTemplate);	
	}

	@Test
	public void testGetForcastForThrowsInvocationWhenForcastIORespondsWithBadHTTPStatus() {
		mockServer.expect(MockRestRequestMatchers.anything())
        	.andExpect(MockRestRequestMatchers.method(HttpMethod.GET))
        	.andRespond(MockRestResponseCreators.withStatus(HttpStatus.BAD_REQUEST));
		try {
			service.getForcastFor("100", "-100");
			fail();
		} catch (InvocationException ex) {
			assertEquals(HttpStatus.BAD_REQUEST.value(), ex.getExternalServiceHTTPStatusCode());
		}
		mockServer.verify();
	}


	@Test
	public void testGetForcastForReturnsForcastResponseObjectOnValidInvocation() throws JsonProcessingException {
		String jsonResponse = buildDummyJSONResponse();
		mockServer.expect(MockRestRequestMatchers.anything())
    		.andExpect(MockRestRequestMatchers.method(HttpMethod.GET))
    		.andRespond(MockRestResponseCreators.withSuccess(jsonResponse, MediaType.APPLICATION_JSON));
		WeatherForecastResponse actualResponse = service.getForcastFor("100", "-200");
		assertNotNull(actualResponse);
		assertEquals(-200, actualResponse.getLatitude());
		assertEquals(100, actualResponse.getLongitude());
		mockServer.verify();
	}

	@Test
	public void testPropertiesAreWiredCorrectly() {
		assertThat(service.darkskyUrl, StringStartsWith.startsWith("https://api.darksky.net"));
	}

	private String buildDummyJSONResponse() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		WeatherForecastResponse stubResponse = new WeatherForecastResponse();
		stubResponse.setLatitude(-200);
		stubResponse.setLongitude(100);
		String jsonString = mapper.writeValueAsString(stubResponse);
		return jsonString;
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testGetForcastForThrowsGatewayExceptionWhenGenericExceptionThrownInvokingService() {
		RestTemplate mockRestTemplate = Mockito.mock(RestTemplate.class);
		Map<String, String> arguments = new HashMap<String,String>();
		when(mockRestTemplate.getForObject(service.darkskyUrl, WeatherForecastResponse.class, arguments))
		.thenThrow(GatewayException.class);
		ForecastServiceImpl serviceThrowingException = new ForecastServiceImpl();
		try {
			serviceThrowingException.getForcastFor("100", "-100");
			fail();
		} catch (GatewayException ex) {
			assertEquals(ForecastServiceImpl.FORECAST_IO_SERVICE_NAME, ex.getServiceName());
			assertNotNull(ex.getInnerException());
		}
	}
	
}
