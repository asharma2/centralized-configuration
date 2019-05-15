package org.springframework.card.cache;

import java.util.Properties;

import org.springframework.stereotype.Service;

@Service
public class PropertiesCache {

	public Properties findByEnv(String env) {
		if ("dev".equalsIgnoreCase(env)) {
			Properties p = new Properties();
			p.put("a", "AAA");
			p.put("b", "BBB");
			p.put("c", "CCC");
			return p;
		}

		if ("qa".equalsIgnoreCase(env)) {
			Properties p = new Properties();
			p.put("a", "AA");
			p.put("b", "BB");
			p.put("c", "CC");
			return p;
		}

		Properties p = new Properties();
		p.put("a", "A");
		p.put("b", "B");
		p.put("c", "C");
		return p;
	}
}
