package com.quarkus.weather.config;

//import org.eclipse.microprofile.config.inject.ConfigProperties;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;

//@ConfigProperties
@ApplicationScoped
public class WeatherProperties {

	@ConfigProperty(name="weather")
	protected String city;

	public String getCity() {
		return city;
	}
//	@Inject
	public void setCity(String city) {
		this.city = city;
	}
}
