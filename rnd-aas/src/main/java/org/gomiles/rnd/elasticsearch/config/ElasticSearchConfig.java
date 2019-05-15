package org.gomiles.rnd.elasticsearch.config;

import java.net.InetAddress;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "org.gomiles.rnd.elasticsearch.repository")
public class ElasticSearchConfig {

	@Value("${spring.data.elasticsearch.cluster-nodes}")
	private String esHost;

	@Value("${spring.data.elasticsearch.cluster-name}")
	private String esClusterName;

	@Bean
	public Client client() throws Exception {
		System.setProperty("es.set.netty.runtime.available.processors", "false");
		Settings settings = Settings.builder().put("cluster.name", esClusterName).build();
		TransportClient client = new PreBuiltTransportClient(settings);

		String[] hostConfigurations = esHost.split(":");
		client.addTransportAddress(new TransportAddress(InetAddress.getByName(hostConfigurations[0]),
				Integer.parseInt(hostConfigurations[1])));
		return client;
	}

	@Bean
	public ElasticsearchOperations elasticsearchTemplate() throws Exception {
		return new ElasticsearchTemplate(client());
	}
}
