package geektime.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class WeatherDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherDemoApplication.class, args);
	}

}
