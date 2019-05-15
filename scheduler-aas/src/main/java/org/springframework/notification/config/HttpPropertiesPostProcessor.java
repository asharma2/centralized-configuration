package org.springframework.notification.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;

public class HttpPropertiesPostProcessor implements EnvironmentPostProcessor {

	@Value("${app.config.endpoint}")
	String appPropUrl;

	private static final String PROPERTY_SOURCE_NAME = "applicationProperties";

	@Override
	public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
		Map<String, Object> propertySource = new HashMap<>();
		try {
			propertySource.put("a", "ATUL");
			propertySource.put("b", "SHARMA");
			environment.getPropertySources().addFirst(new MapPropertySource(PROPERTY_SOURCE_NAME, propertySource));

		} catch (Exception e) {
			throw new RuntimeException("Error fetching properties from db");
		}
	}

}
