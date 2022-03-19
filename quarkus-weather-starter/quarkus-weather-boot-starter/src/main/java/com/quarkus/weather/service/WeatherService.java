package com.quarkus.weather.service;

import com.quarkus.weather.config.WeatherProperties;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class WeatherService {
	@Inject
	public void setProperties(WeatherProperties properties) {
		this.properties = properties;
	}

	private WeatherProperties properties;

	public String printCity() {
//		return Uni.createFrom().item("当前城市： " + properties.getCity()).toString();
		return "当前城市： " + properties.getCity();
	}
	
	public String getCityWeather(String city) throws Exception {
		String url = "http://wthrcdn.etouch.cn/weather_mini?city=" + city;
		OkHttpClient okHttpClient = new OkHttpClient();
		final Request request = new Request.Builder()
		        .url(url)
		        .build();
		final Call call = okHttpClient.newCall(request);
        Response response = call.execute();
        String ret =  response.body().string();
        System.out.println("查询天气数据：" + ret);
//		return Uni.createFrom().item(ret).toString();
		return ret;
	}
	
	public String getCityWeather() throws Exception {
//		return Uni.createFrom().item(properties.getCity()).toString();
		return properties.getCity();
	}
}
