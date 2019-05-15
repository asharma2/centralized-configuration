package org.gomiles.rnd.influxdb.config;

import org.gomiles.rnd.influxdb.InfluxDBConnectionFactory;
import org.gomiles.rnd.influxdb.converter.MemoryPointConverter;
import org.gomiles.rnd.influxdb.model.MemoryPoint;
import org.gomiles.rnd.influxdb.operations.InfluxDBTemplate;
import org.gomiles.rnd.influxdb.properties.InfluxDBProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@DependsOn(value = "influxDBProperties")
@Configuration
public class InfluxDBConfig {

	@Autowired
	InfluxDBProperties influxDBProperties;

	@Bean(initMethod = "start", destroyMethod = "close")
	InfluxDBConnectionFactory influxDBConnectionFactory() {
		return new InfluxDBConnectionFactory(influxDBProperties);
	}

	@Bean
	InfluxDBTemplate<MemoryPoint> influxDBTemplate() {
		return new InfluxDBTemplate<>(new MemoryPointConverter(), influxDBConnectionFactory());
	}

}
