package com.heanbian.boot.autoconfigure.elasticsearch;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.heanbian.block.elasticsearch.client.ElasticsearchTemplate;

@Configuration
@EnableConfigurationProperties(ElasticsearchProperties.class)
public class ElasticsearchAutoConfiguration {

	@Bean
	@ConditionalOnProperty(prefix = "heanbian.elasticsearch", name = "enabled", havingValue = "true")
	public ElasticsearchTemplate elasticsearchTemplate(ElasticsearchProperties ep) {
		return new ElasticsearchTemplate(ep.getClusterNodes());
	}

}
