package geektime.starter;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
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

	@Cacheable(value = "spittleCache")
	@RequestMapping(value = "/weather/all", produces = { "text/plain;charset=UTF-8" })
	public String findByName() throws Exception {
		Long allBeginTime = System.currentTimeMillis();

		String[] allCities = new String[]{"北京", "上海", "广州", "深圳", "成都"};
		Integer[] accessTimes = new Integer[]{50, 40, 30, 20, 10};

		JSONObject jsonObject = new JSONObject();

		for(int l = 0; l<allCities.length; l++){
			Long beginTime = System.currentTimeMillis();
			for(int i=0; i<accessTimes[l]; i++){
				weatherService.getCityWeather(allCities[l]);
			}
			Long endTime = System.currentTimeMillis();
			Long triedTime = endTime - beginTime;
			jsonObject.put(allCities[l]+"访问了"+ accessTimes[l] +"次", triedTime);
		}

		Long allEndTime = System.currentTimeMillis();
		Long allTriedTime = allEndTime - allBeginTime;
		jsonObject.put("总访问了", allTriedTime);
		System.out.println("总访问了： "+allTriedTime);

		return jsonObject.toString();
	}
}
