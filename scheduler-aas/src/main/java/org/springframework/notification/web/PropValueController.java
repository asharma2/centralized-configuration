package org.springframework.notification.web;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/propval")
public class PropValueController {

	@Value("${app.config.endpoint}")
	String appPropUrl;
	@Autowired
	Properties confProperties;

	@GetMapping("/{key}")
	public String getValue(@PathVariable("key") String key) {
		return confProperties.getProperty(key);
	}

	@GetMapping("/refresh/{key}")
	public String refresh(@PathVariable("key") String key) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<?> entity = new HttpEntity<>(headers);
		ResponseEntity<Properties> p = restTemplate.exchange(appPropUrl, HttpMethod.GET, entity, Properties.class, key);
		confProperties.putAll(p.getBody());
		return "SUCCESS";
	}
}
