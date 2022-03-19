package com.quarkus.weather.boot;

import com.quarkus.weather.service.MutinyWeatherService;
import io.micrometer.core.annotation.Counted;
import io.smallrye.mutiny.Uni;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/uniweather")
public class MutinyWeatherResource {

	private MutinyWeatherService mutinyWeatherService;
	@Inject
	public void setMutinyWeatherService(MutinyWeatherService mutinyWeatherService) {
		this.mutinyWeatherService = mutinyWeatherService;
	}

	@Counted
	@GET
	@Produces({MediaType.TEXT_PLAIN})
	@Path("/currentcity")
	public Uni<String> printCity() {
		return mutinyWeatherService.printCity();
	}

	@Counted
	@GET
	@Produces({MediaType.TEXT_PLAIN})
	@Path("/city-weather")
	public String weatherOfCity() throws Exception {
		return mutinyWeatherService.getCityWeather();
	}

	@Counted
	@GET
	@Produces({MediaType.TEXT_PLAIN})
	@Path("/city/{city}")
	public Uni<String> weatherOfCity(@PathParam("city") String city) throws Exception {
		return mutinyWeatherService.getRealCityWeather(city);
	}

	@Counted
	@GET
	@Produces({MediaType.TEXT_PLAIN})
	@Path("/city/{city}")
	public Uni<String> getAsyncWeather(@PathParam("city") String city) throws Exception {
		return mutinyWeatherService.getRealCityWeather(city);
	}
}
