package geektime.starter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {

	@Autowired
	private WeatherService weatherService;

	@RequestMapping(value = "/currentcity", produces = { "text/plain;charset=UTF-8" })
	public String printCity() {

		return weatherService.printCity();
	}

	@RequestMapping(value = "/weather", produces = { "text/plain;charset=UTF-8" })
	public String weatherOfCity() throws Exception {

		return weatherService.getCityWeather();
	}
	
	@RequestMapping(value = "/weather/{city}", produces = { "text/plain;charset=UTF-8" })
	public String weatherOfCity(@PathVariable("city") String city) throws Exception {

		return weatherService.getCityWeather(city);
	}
}
