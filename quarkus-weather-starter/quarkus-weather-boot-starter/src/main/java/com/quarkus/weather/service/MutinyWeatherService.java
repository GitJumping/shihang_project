package com.quarkus.weather.service;

import com.quarkus.weather.EmitThread;
import com.quarkus.weather.config.WeatherProperties;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.concurrent.Executor;

@ApplicationScoped
public class MutinyWeatherService {
	@Inject
	public void setProperties(WeatherProperties properties) {
		this.properties = properties;
	}

	private WeatherProperties properties;

	public Uni<String> printCity() {
		return Uni.createFrom()
				.item(()->{return "当前城市： " + properties.getCity();});
	}
	
	public Uni<String> getRealCityWeather(String city){
		//String ret = getWeatherList(city);
		System.out.println("开始调用了>");
		Executor executor = new EmitThread(city);
		return Uni.createFrom()
				//.item(()->{return getWeatherList(city);})
				.item(()->{return "";})
				.emitOn(executor)
				.onItem().transform(s-> {
					System.out.println("开始执行了<");
					return s;
				});
	}

	public void printCity(String city) {
		return ;
	}

	public String getCityWeather() throws Exception {
//		return Uni.createFrom().item(properties.getCity()).toString();
		return properties.getCity();
	}
}
