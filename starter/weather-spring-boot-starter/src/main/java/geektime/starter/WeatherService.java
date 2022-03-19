package geektime.starter;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Call;

public class WeatherService {
	private WeatherProperties properties;

	public WeatherService() {
	}

	public WeatherService(WeatherProperties properties) {
		this.properties = properties;
	}

	public String printCity() {
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
		return ret;
	}
	
	public String getCityWeather() throws Exception {
	
		return getCityWeather(properties.getCity());
	}
}
