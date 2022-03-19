package geektime.starter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(WeatherProperties.class)
@ConditionalOnClass(WeatherService.class)
@ConditionalOnProperty(prefix = "weather", value = "enable", matchIfMissing = true)
public class WeatherAutoConfiguration {

	@Autowired
	private WeatherProperties properties;

	@Bean
	@ConditionalOnMissingBean(WeatherService.class)
	public WeatherService weatherService() {
		return new WeatherService(properties);
	}

}
