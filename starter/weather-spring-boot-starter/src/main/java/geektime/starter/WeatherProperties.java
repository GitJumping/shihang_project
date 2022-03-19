package geektime.starter;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties
public class WeatherProperties {
	private String city;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
}
