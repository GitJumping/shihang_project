package com.quarkus.weather.boot;

import com.quarkus.weather.service.WeatherService;
import io.micrometer.core.annotation.Counted;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/weather")
public class WeatherResource {

	private WeatherService weatherService;
	@Inject
	public void setWeatherService(WeatherService weatherService) {
		this.weatherService = weatherService;
	}

	//@RequestMapping(value = "/currentcity", produces = { "text/plain;charset=UTF-8" })
	@Counted
	@GET
	@Produces({MediaType.TEXT_PLAIN})
	@Path("/currentcity")
	public String printCity() {
		return weatherService.printCity();
	}

	//@RequestMapping(value = "/weather", produces = { "text/plain;charset=UTF-8" })
	@Counted
	@GET
	@Produces({MediaType.TEXT_PLAIN})
	@Path("/city-weather")
	public String weatherOfCity() throws Exception {
		return weatherService.getCityWeather();
	}
	
	//@RequestMapping(value = "/weather/{city}", produces = { "text/plain;charset=UTF-8" })
	@Counted
	@GET
	@Produces({MediaType.TEXT_PLAIN})
	@Path("/city/{city}")
	public String weatherOfCity(@PathParam("city") String city) throws Exception {
		return weatherService.getCityWeather(city);
	}
}
