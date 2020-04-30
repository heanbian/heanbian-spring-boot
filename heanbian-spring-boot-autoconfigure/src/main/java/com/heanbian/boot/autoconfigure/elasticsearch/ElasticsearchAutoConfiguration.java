package com.heanbian.boot.autoconfigure.elasticsearch;

import static org.apache.http.auth.AuthScope.ANY;

import java.util.List;

import org.apache.http.HttpHost;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestClientBuilder.HttpClientConfigCallback;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.heanbian.block.elasticsearch.client.ConnectionString;
import com.heanbian.block.elasticsearch.client.ElasticsearchTemplate;

@Configuration
@EnableConfigurationProperties(ElasticsearchProperties.class)
public class ElasticsearchAutoConfiguration {

	@Bean
	@ConditionalOnProperty(prefix = "heanbian.elasticsearch", name = "enable", havingValue = "true")
	public ElasticsearchTemplate elasticsearchTemplate(ElasticsearchProperties ep) {
		return new ElasticsearchTemplate(getRestHighLevelClient(ep));
	}

	private RestHighLevelClient getRestHighLevelClient(ElasticsearchProperties ep) {
		ConnectionString conn = new ConnectionString(ep.getClusterNodes());
		List<String> nodes = conn.getHosts();
		final int size = nodes.size();
		HttpHost[] hosts = new HttpHost[size];

		for (int i = 0; i < size; i++) {
			String[] s = nodes.get(i).split(":");
			if (s.length == 2) {
				hosts[i] = new HttpHost(s[0], Integer.valueOf(s[1]));
			}
		}

		RestClientBuilder rb = RestClient.builder(hosts);
		if (conn.getUsername() != null && conn.getPassword() != null) {
			final CredentialsProvider credentials = new BasicCredentialsProvider();
			credentials.setCredentials(ANY, new UsernamePasswordCredentials(conn.getUsername(), conn.getPassword()));

			rb.setHttpClientConfigCallback(new HttpClientConfigCallback() {
				@Override
				public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
					return httpClientBuilder.setDefaultCredentialsProvider(credentials);
				}
			});
		}

		return new RestHighLevelClient(rb);
	}

}
