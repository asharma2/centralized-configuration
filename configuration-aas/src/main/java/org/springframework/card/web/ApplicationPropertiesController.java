package org.springframework.card.web;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.card.cache.PropertiesCache;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/prop")
public class ApplicationPropertiesController {

	@Autowired
	PropertiesCache propertiesCache;

	@GetMapping("/{env}")
	public Properties getProperties(@PathVariable("env") String env) {
		return propertiesCache.findByEnv(env);
	}
}
